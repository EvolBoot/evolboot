#!/bin/sh
export projectPackage="org.evolboot"
export tableprefix="evol"
export author="evol"

export boundContext="pay"
export boundContextClass="Pay"
export boundContextUrl="pay"

export module="receiptorder"
export classNamePrefix="ReceiptOrder"
export instantiationObjectName="receiptOrder"
export classNameUrl="receipt-order"

export zh="测试"
export datePlaceholder=`date  +'%Y\\/%m\\/%d'`
export pkIdClass="String"


rm ./module -rf
cp /opt/code-generator/evol module -R
sh /opt/code-generator/projects/evol-gen.sh
