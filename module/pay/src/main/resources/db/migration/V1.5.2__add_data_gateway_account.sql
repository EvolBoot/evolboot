INSERT INTO evoltb_pay_gateway_account (id_, create_at_, update_at_, version_, logo_,
                                        locales_, merchant_id_, appid_, secret_key_,
                                        minimum_receipt_, maximum_receipt_, enable_, pay_gateway_, sort_)
VALUES (1, '2025-11-19 14:22:36', '2025-11-19 14:22:36', 0, '${storage-base-url}/default/pay/visa.png',
        '[
    {
      "locale": "zh_CN",
      "name": "虚拟币支付"
    },
    {
      "locale": "en_US",
      "name": "Crypto Payment"
    }
  ]',
        '213C3GW-DP54P9F-JDAWWXW-3Q51RQN', '', '', 1.00, 10000.00, 1, 'NOW_PAYMENTS',
        0);