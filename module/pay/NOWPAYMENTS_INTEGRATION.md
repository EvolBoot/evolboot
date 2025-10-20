# NOWPayments 虚拟币支付网关集成说明

## 概述

NOWPayments 是一个支持 100+ 种虚拟货币的支付网关服务。本集成提供了代收(收款)功能,支持 ETH, BTC, USDT, USDC 等主流虚拟货币。

## 配置步骤

### 1. 获取 NOWPayments 凭证

1. 注册 NOWPayments 账户: https://nowpayments.io
2. 在控制台获取:
   - **API Key**: 用于调用 API
   - **IPN Secret**: 用于验证回调签名

### 2. 配置 application.yml

```yaml
evolboot:
  pay:
    domain: https://your-domain.com  # 你的域名
    now-payments-api-key: YOUR_API_KEY_HERE
    now-payments-ipn-secret: YOUR_IPN_SECRET_HERE
    now-payments-sandbox: true  # 测试环境设为 true,生产环境设为 false
```

### 3. 创建支付网关账户

在系统中创建 NOWPayments 网关账户配置:

```http
POST /api/v1/pay/gateway-account
Content-Type: application/json

{
  "payGateway": "NOW_PAYMENTS",
  "merchantId": "your-merchant-id",  // 可选,用于区分多个账户
  "secretKey": "YOUR_IPN_SECRET",    // IPN Secret
  "minimumReceipt": 10.00,           // 最小支付金额(USD)
  "maximumReceipt": 10000.00,        // 最大支付金额(USD)
  "enable": true,
  "locales": [
    {
      "locale": "zh_CN",
      "name": "虚拟币支付"
    },
    {
      "locale": "en_US",
      "name": "Crypto Payment"
    }
  ]
}
```

## 使用方式

### 创建支付订单

```http
POST /api/v1/pay/receipt-order/create
Content-Type: application/json

{
  "internalOrderId": "ORDER_123456",
  "productName": "商品支付",
  "payAmount": 100.00,
  "currency": "ETH",  // 支持: ETH, BTC, USDT, USDC
  "payGatewayAccountId": 123,
  "payeeName": "张三",
  "payeeEmail": "user@example.com"
}
```

响应:
```json
{
  "success": true,
  "data": {
    "receiptOrderId": "PAYIN20250109xxxxx",
    "payAddress": "0x1234567890abcdef...",  // 虚拟币收款地址
    "payAmount": 0.05234,  // 需要支付的虚拟币数量
    "payCurrency": "eth",
    "expirationTime": "2025-01-09T12:00:00Z"
  }
}
```

### IPN 回调通知

NOWPayments 会向以下地址发送支付状态通知:
```
POST https://your-domain.com/api/v1/pay/payment/now-payments/receipt/notify
```

回调 Header:
```
x-nowpayments-sig: <HMAC-SHA512签名>
```

回调 Body:
```json
{
  "payment_id": "5077125196",
  "payment_status": "finished",
  "pay_address": "0x1234...",
  "price_amount": 100.00,
  "price_currency": "usd",
  "pay_amount": 0.05234,
  "actually_paid": 0.05234,
  "pay_currency": "eth",
  "order_id": "PAYIN20250109xxxxx",
  "outcome_amount": 99.50,
  "outcome_currency": "usd"
}
```

## 支付状态说明

| NOWPayments 状态 | 系统状态 | 说明 |
|------------------|----------|------|
| waiting | PENDING | 等待用户支付 |
| confirming | PENDING | 区块链确认中 |
| confirmed | SUCCESS | 已确认 |
| finished | SUCCESS | 支付完成 |
| failed | FAIL | 支付失败 |
| refunded | FAIL | 已退款 |
| expired | FAIL | 订单过期 |

## 支持的虚拟货币

| 货币代码 | 名称 | NOWPayments 代码 |
|---------|------|------------------|
| ETH | Ethereum | eth |
| BTC | Bitcoin | btc |
| USDT | Tether (ERC20) | usdterc20 |
| USDC | USD Coin (ERC20) | usdcerc20 |

## 安全注意事项

1. **IPN 签名验证**: 系统会自动验证 NOWPayments 的 IPN 签名,确保回调请求的真实性
2. **HTTPS**: 生产环境必须使用 HTTPS
3. **金额校验**: 回调时会校验支付金额是否匹配
4. **幂等性**: 系统会自动处理重复的回调通知

## 测试环境

NOWPayments 提供 Sandbox 环境用于测试:
- Sandbox API: https://api-sandbox.nowpayments.io
- 需要单独的 Sandbox API Key
- 设置 `now-payments-sandbox: true` 启用测试模式

## 常见问题

### Q: 支付确认需要多长时间?
A: 取决于区块链网络:
- BTC: 约 10-60 分钟
- ETH: 约 3-5 分钟
- USDT/USDC: 约 3-5 分钟

### Q: 手续费如何计算?
A: NOWPayments 会从支付金额中扣除手续费,`outcome_amount` 为实际到账金额。

### Q: 支持哪些结算货币?
A: 支持稳定币结算(USDT, USDC)或法币(USD, EUR 等),需在 NOWPayments 控制台配置。

### Q: 如何处理汇率波动?
A: NOWPayments 在创建订单时会锁定汇率,通常锁定时间为 10-15 分钟。

## 技术支持

- NOWPayments 文档: https://documenter.getpostman.com/view/7907941/S1a32n38
- NOWPayments 支持: support@nowpayments.io
