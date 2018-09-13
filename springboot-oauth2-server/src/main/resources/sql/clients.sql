CREATE TABLE `oauth_client_details` (
  `client_id`               VARCHAR(15) NOT NULL,
  `resource_ids`            VARCHAR(15)   DEFAULT NULL,
  `client_secret`           VARCHAR(30)   DEFAULT NULL,
  `scope`                   VARCHAR(50)   DEFAULT NULL,
  `authorized_grant_types`  VARCHAR(100)  DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(200)  DEFAULT NULL,
  `authorities`             VARCHAR(50)   DEFAULT NULL,
  `access_token_validity`   INT(11)       DEFAULT NULL,
  `refresh_token_validity`  INT(11)       DEFAULT NULL,
  `additional_information`  VARCHAR(4096) DEFAULT NULL,
  `autoapprove`             VARCHAR(10)   DEFAULT NULL,
  PRIMARY KEY (`client_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('client', NULL, '{noop}secret', 'all', 'password,authorization_code,refresh_token,implicit,client_credentials',
                  'http://127.0.0.1:8070/ui/redirect', NULL, NULL, NULL, NULL, 'true');