
-- Create DB: asteriskcdrdb
CREATE DATABASE asteriskcdrdb;

-- Table: users
CREATE TABLE `asteriskcdrdb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


-- Table: cdr

CREATE TABLE `asteriskcdrdb`.`cdr` (
  `calldate` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
  `src` VARCHAR(80) NOT NULL DEFAULT '',
  `dst` VARCHAR(80) NOT NULL DEFAULT '',
  `billsec` INT NOT NULL DEFAULT 0,
  `disposition` VARCHAR(45) NOT NULL DEFAULT '',
  `uniqueid` INT NOT NULL AUTO_INCREMENT,
  `recordingfile` VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`uniqueid`));


-- Insert values table: users

INSERT INTO `asteriskcdrdb`.`users` (`id`, `login`, `password`) VALUES ('1', 'trapeza', '123');
INSERT INTO `asteriskcdrdb`.`users` (`id`, `login`, `password`) VALUES ('2', 'demetra', '321');
INSERT INTO `asteriskcdrdb`.`users` (`id`, `login`, `password`) VALUES ('3', 'admin', '777');

-- Insert values table: cdr

INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-07-27 00:15.0', '7001', '9892137623907', '0', 'NO ANSWER', 'force-989213709907-7001-20170727-001528-1501103728.3076.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-07-27 00:24.0', '700964325341', '7700', '12', 'ANSWERED', '/var/spool/asterisk/monitor/2017/07/27/force-s-unknown-20170727-002427-1501104267.3080.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-07-27 11:11.0', '97028933', '9892135426907', '9', 'ANSWERED', '/var/spool/asterisk/monitor/2017/07/27/force-989213709907-97021013-20170727-111206-1501143116.3097.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-06-26 18:40.0', '989213762807', '989213708407', '0', 'NO ANSWER', 'force-989213709907-989213709907-20170626-184051-1498491641.28409.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-06-26 18:59.0', '7007', '7005', '117', 'ANSWERED', '/var/spool/asterisk/monitor/2017/06/26/exten-7005-7007-20170626-185933-1498492773.28440.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-06-26 19:06.0', '989213762889', '7700', '6', 'ANSWERED', '/var/spool/asterisk/monitor/2017/06/26/force-s-unknown-20170626-190632-1498493192.28464.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-07-01 18:21.0', '6702', '6801', '8', 'ANSWERED', '/var/spool/asterisk/monitor/2017/07/01/exten-7801-6702-20170701-182150-1498922510.2244.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-07-06 23:40.0', '6702', '6801', '10', 'ANSWERED', '/var/spool/asterisk/monitor/2017/07/06/exten-7801-6702-20170706-234033-1499373633.464.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-07-10 21:07.0', '6801', '6702', '15', 'ANSWERED', '/var/spool/asterisk/monitor/2017/07/10/exten-7702-6801-20170710-210742-1499710062.2070.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-06-27 00:39.0', '6702', '6801', '43', 'ANSWERED', '/var/spool/asterisk/monitor/2017/06/27/exten-7801-6702-20170627-003920-1498513160.28633.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-07-03 20:02.0', '6702', '6801', '4', 'ANSWERED', '/var/spool/asterisk/monitor/2017/07/03/exten-7801-6702-20170703-200209-1499101329.3399.wav');
INSERT INTO `asteriskcdrdb`.`cdr` (`calldate`, `src`, `dst`, `billsec`, `disposition`, `recordingfile`) VALUES ('2017-07-06 22:54.0', '6702', '6801', '44', 'ANSWERED', '/var/spool/asterisk/monitor/2017/07/06/exten-7801-6702-20170706-225424-1499370864.455.wav');

