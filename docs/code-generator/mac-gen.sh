#!/bin/sh
export projectPackage="org.evolboot"
export tableprefix="evol"
export author="evol"

export boundContext="thirdpartypay"
export boundContextClass="ThirdPartyPay"
export boundContextUrl="third-party-pay"

export module="receiptorder"
export classNamePrefix="ReceiptOrder"
export instantiationObjectName="receiptOrder"
export classNameUrl="receipt-order"

export zh="测试"
export datePlaceholder=`date  +'%Y\\/%m\\/%d'`
export pkIdClass="String"


rm ./module -rf
cp /opt/code-generator/template module -R
sh /opt/code-generator/projects/gen.sh
