关于EvolBoot的一些开发理念和开发流程(DDD部分)

# 
### 基础的项目结构
```
├── docs # 文档或者其他
│ ├── code-generator
│ ├── error-cdoe.md #一些错误码范围的备注,可忽略
│ └── migration-version.md 每个模块的版本号
├── module # 所有模块
│ ├── bff # 面向多表复合查询,可用Mybatis 做联合查询
│ ├── component # 一些通用组件库
│ │ ├── apidoc # API文档生成
│ │ ├── captcha # 验证码
│ │ ├── code-generator #代码生成（Java简单版）
│ │ ├── config # 动态配置，使用json格式存在数据库中,使用Redis作为缓存,自定义配置
│ │ ├── mq # 消息队列模块
│ │ ├── sentinel # 限流模块
│ │ ├── sms # 短信
│ │ ├── storage #存储
│ │ └── websocket # websocket
│ ├── config-resource # 配置文件
│ ├── core # 一些通用的工具或者配置可以放这边
│ ├── entry # 应用程序入口模块，可通过引用不同的模块，让不同的入口程序有不同的API
│ │ ├── entry-admin # 后台API专用接口
│ │ ├── entry-app # 商户或者用户专用入户
│ │ ├── entry-schedule # 定时任务专用入口
│ │ ├── entry-test # 测试使用
│ ├── identity # 用户、身份证明，和权限相关
│ ├── pay # 支付模块（暂时不用）
│ ├── schedule # 定时任务专用，引用各个模块
│ ├── security
│ │ ├── security-api # 全局可用，负责校验权限和获取当前登录的用户信息
│ │ ├── security-token # 负责登录和返回token，可单独部署
│ ├── shared # 业务和事件共享模块，纯粹的，除了lombok 禁止引入第三方库
│ ├── system # 一些无处安放的系统模块，比如banner,字段,新闻,qa ，操作日志，登录日志等等
├── static # 静态文件，比如默认头像等，不用管
```
### DDD的一些基础相关概念
DDD 领域驱动设计，也可以理解为充血模型，跟传统的贫血模型相比，充血模型关注点放在业务对象(状态)上。  
**什么是贫血模型？**
贫血模型可以认为是单纯的`POJO`，一个POJO的对象上一般只有 字段,set/get, 而贫血的Entity(实体) 就跟VO，DTO，之类一样，
仅仅作为一个数据承载的对象，然后将业务状态放在 Service 中，由Service去控制，之后再通过 set方法 设置到Entity（或者DTO），然后调用update/save之类保存，这就是传统的贫血模型。  
**而什么充血模型？**
充血模型中的Entity，使用真正的面向对象思维去开发，它是一个"活生生"的对象，它的字段就是它自身状态，它可以允许"调用方"通过set方法去改变它的内部状态，但也可以拒绝(不开放set方法),
因为它要保证它的状态一定是"正确"的，它需要维护它自身的状态。
比如"账户余额"对象，内部有字段:
```java
private BigDecimal availableBalance
```  
它不能让外部直接调用`setAvailableBalance(..)` 去改变它的余额信息,
但它提供: `public void add(BigDecimal amount)` 和 `public void subtract(BigDecimal amount)` 方法去让外部控制它的余额, 如果在调用 `subtract` 时余额不足，它会直接抛出异常，
这就是它需要维护的状态的正确性。  
充血模型对象，在DDD中，可以简单称之为"聚合根"。  
聚合根只会关心它自身的状态，而我们业务是复杂（复合）的，需要多个对象之间进行交互，比如`「订单领域」`，一个订单的创建需要多个领域（服务）之间的互动，
比如检查购买的 "商品" 是否存在，"库存"数量是否足够，检查"账户余额"是否足够等等，这边描述的 `商品`，`库存`，`账户余额` 就是三个领域了，此时就需要引入"领域服务"进行协调，
这个就跟我们平时三层架构类似，但又用点不太一样，比如，之前的开发可能是，伪代码如下：
```java  
public class OrderService {
private ProductDao productDao;
private InventoryInfoDao inventoryInfoDao;
private AccountDao accountDao;
private OrderDao orderDao;  

public void createOrder(Long userId, Long productId, int quantity) {
Product product = productDao.selectById(productId);
if (product == null) {
throw new RuntimeException("商品不存在");
}
if (product.getStatus() == 0) {
throw new RuntimeException("商品不允许购买");
}
// 其他商品的判断  

Inventory inventoryInfo = inventoryInfoDao.selectByProductId(product);
if (inventoryInfo == null) {
throw new RuntimeException("库存不足");
}
if (inventoryInfo.getQuantity() < quantity) {
throw new RuntimeException("库存不足");
}
// 其他库存的判断  
// 扣减库存
inventoryInfo.setQuantity(inventoryInfo.getQuantity() - quantity);
inventoryInfoDao.save(inventoryInfo);  
BigDecimal amount = product.getPrice() * quantity;  
Account account = accountDao.selectByUserId(userId);
if (account.getAvailableBalance() < amount) {
throw new RuntimeException("余额不足");
}
// 一些列的检查
// 然后扣除
account.setAvailableBalance(account.getAvailableBalance() - amount);
// 这种还算好的，如果通过直接执行(update 语句,那就更难排查了)
accountDao.save(account);  
// 最后
Order order = new Order();
order.setProductId(productId);
order.setUserId(userId);
order.setTotalPrice(amount);
// 然后一堆的set  
// 保存order
orderDao.save(order);  
}
}  
```  
以上就是传统的思路，也不是不能用，而且开发时，这种方式还很自由，想到哪写到哪，简单直接，这也是这种风格的优势，凡是有点基础的都可以看的懂，且改起来也不麻烦，这种风格一般称之为"过程式"或者"面条式"代码。
那这种方式，有什么缺点呢？
有的，如果业务复杂到一定程度，这样风格的代码维护起来极其累，比如到底余额什么时候被改了，不知道，因为所有地方都可以执行
` account.setAvailableBalance(account.getAvailableBalance() - amount);`
然后这个时候，你觉得可能需要搞个日志，来记录什么时候变化了余额，以及余额变化的前后的明细，
但这样的话，所有有调用`account.setAvailableBalance`的地方都需要加日志，于是你觉得麻烦了，这个时候你会自然而然的开始重构，
将这些改为一个单独的服务，然后对外提供服务接口，其实，这也服务了面向对象的一种设计原则 `"高内聚，低耦合"`, 做自己的事就好。  
这个就是我们要接下来要说的，已知会遇到这些问题，我们提前规避，最好作为一个开发规范，这个规范就是：
1. 禁止直接调用其他领域的Dao(Repository)
2. 禁止直接修改其他领域对象的状态
3. 有需要第三方领域协调的，必须通过第三方领域开放的服务进行  
   简单的三点，好了，用以上规范重构下上面的代码，伪代码可能如下：
```java  
import java.math.BigDecimal;  
public class ProductService {  
private ProductDao productDao;  
public void check(Long productId) {
Product product = productDao.selectById(productId);
if (product == null) {
throw new RuntimeException("商品不存在");
}
if (product.getStatus() == 0) {
throw new RuntimeException("商品不允许购买");
}
// 其他检查
}  
// 获取单价
public BigDecimal getUnitPrice(Long productId) {
Product product = productDao.selectById(productId);
return product.getUnitPrice();
}  
}  
// 库存对象
public class InventoryInfo {  
private int quantity;  
// 领域自己可以做的，就自己做
public void deduction(int _quantity) {
if (quantity < _quantity) {
throw new RuntimeException("库存不足");
}
quantity = quantity - _quantity;
}  
}  
public class InventoryService {  
private InventoryDao inventoryDao;  
public void deductionQuantity(Long productId, int quantity) {
Inventory inventoryInfo = inventoryDao.selectByProductId(product);
if (inventoryInfo == null) {
throw new RuntimeException("库存不足");
}  
// 扣减库存,注意这边使用的是领域的方法，而不是直接set,库存足不足它自己判断
inventoryInfo.deduction(quantity);
inventoryDao.save(inventoryInfo);
}  
}  
public class Account {  
private BigDecimal balance;  
public void subtract(BigDecimal amount) {
if (balance < amount) {
throw new RuntimeException("余额不足");
}
this.balance = balance - amount;
}  
}  

public class AccountService {
private AccountDao accountDao;  
public void subtract(Long userId, BigDecimal amount) {
Account account = accountDao.selectByUserId(userId);
// 注意,这边也是聚合根自身去检查它余额足不足
account.subtract(amount);
// 然后这边进行记录日志或者抛出事件
accountDao.save(account);
}  
}  

public class OrderService {
private ProductService productService;
private InventoryInfoService inventoryInfoService;
private AccountService accountService;
private OrderDao orderDao;  

public void createOrder(Long userId, Long productId, int quantity) {  
// 检查商品
productService.check(productId);
// 扣减库存
inventoryInfoService.deductionQuantity(productId,quantity);
// 计算价格
BigDecimal unitPrice = productService.getUnitPrice(productId);
BigDecimal totalPrice = unitPrice * quantity;
// 扣除余额
accountService.subtract(userId,totalPrice);  
// 最后构建一个订单信息
Order order = new Order(
productId,
userId,
totalPrice,
....
);
// 保存order
orderDao.save(order);
}
}  
```
好了，这是改造完成的，第一眼看上去很麻烦，这是必须的代价，也是Java被嘲讽的地方（啰嗦），但好处也很明显，但项目越复杂，开发人员也越多时，每个人只需要负责自己的模块，
然后对外提供接口，其他调用人员并不需要了解这些模块的业务规则，只知道调用这个方法就可以了，也有益于后期甩锅（不是），比如余额扣除错了，到底是传错了，还是账户领域那边错了？
另外，维护时，不用去担心自己的数据是否被其他人直接set修改了，因为按照规范，其他人是不允许直接调用set修改的，所以此时，只需要对应数据修改的服务的就可以。  
以上就是一些利用DDD开发的思路，当然，本质上其实就是"高内聚，低耦合"，没有那么难。  
另外，我们还将引入`"应用服务"`，`"查询服务"` `"事件"` 等相关概念，  
*应用服务*： 应用服务 其实就是我们平时的 XxxServiceImpl，但为了明确概念，我会加一个命名，比如 XxxAppServiceImpl ，多了一个 App， 是Application的缩写，用它来统领多个`领域服务`，
这是为什么，多此一举吗？，其实这有一个简单的原则：
1. 应用服务负责技术相关的
2. 协调多个领域服务的交互  
   上面说的有点难以理解，我简化了下：
1. 太多的代码就不要写在应用服务了。  
   免得这个服务几百上下行，然后方法之间互相调用，到时候排查起来很费劲。  
   而真正的业务，就使用`领域服务`去交互好了。  
   *查询服务*：其实仅仅负责对外的查询而已，为啥？ 初衷是考虑到各个领域之间的相互依赖，比如 A -> B , B -> A ，
   此时如果相互引用对方的服务，则就会造成循环依赖，这是需要禁止的，为了这个原因，我所以我加入查询服务，
   后面发现，将查询服务单独出来后，`应用服务`则突然变的很干净，很简洁，所以后续就保留了下来。  
   *事件*： 事件 其实很简单，就是发生过的事实。
   这个用来解耦非常不错，如上面的`账户余额变动`，此时它需要日志，那么就直接抛出`余额变更事件`，这个事件里面记录了前后的变化。
   之后另外一个领域，比如`余额变动日志`领域，
   则直接订阅这个事件，然后记录到数据库，此时`账户余额`这个领域可以完全不改动，也不关心谁记录了它的信息。
   另外，如果此时添加了一个需求，比如用户的账户发生了变动，不管是加钱还是减钱，都需要通知用户，订阅了公众号的需要通知到微信，有填手机号的，需要发短信通知，有填邮箱发送邮件通知。
   这是合理的需求，如果此时去修改`账户余额`领域，增加通知相关的代码，除了很麻烦外，则可能还会影响`账户余额`服务的不可用，或者改出BUG，加上`通知`这个事情是允许失败的，比如短信到期了，邮件发生失败了等等第三方因素，
   但用户的下单流程，不能因为这些原因导致失败，这是不允许的。
   此时如果采用了`事件`，那么`通知领域`只需要监听`余额变更事件`，然后异步去发送这些信息即可，哪怕失败了，大不了过段时间重来，而`账户余额`领域可以完全不去修改，好处显而易见。  
   当然，你也可以说那我用其他方式也可以实现，比如将`变更日志`存到数据库，大家都去数据库定时查，然后再发；思路是一样的，只是说，`事件`这个概念可以让整个沟通更顺畅。  
   以上差不多是DDD的一些概念和开发思路，接下来，说说简单的开发流程，如果使用这个框架，进行快速开发。

### 开发流程
待续