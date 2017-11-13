--------------------------------------------------------
--  File created - п€тница-но€бр€-10-2017   
--------------------------------------------------------
--------------------------------------------------------
--  Script for creating database
--------------------------------------------------------
CREATE TABLE "MYTABLE" (	"ID" NUMBER, 	"VERSION" NUMBER, 	"CAPTION" VARCHAR2(20 BYTE), 	"STATUS" NUMBER   );

Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('1','1','t1','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('1','2','t2','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('1','3','t3','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('2','1','t4','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('3','1','t5','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('2','2','t6','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('2','3','t7','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('4','1','t8','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('3','2','t9','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('5','1','t10','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('4','2','t11','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('4','3','t12','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('5','2','t13','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('5','3','t14','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('6','1','t15','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('6','2','t16','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('6','3','t17','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('7','1','t18','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('1','4','t19','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('4','4','t20','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('3','3','t21','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('2','4','t22','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('7','2','t23','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('1','5','t24','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('8','1','t25','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('1','6','t26','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('2','5','t27','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('9','1','t28','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('8','2','t29','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('9','2','t30','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('6','4','t31','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('10','1','t32','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('10','2','t33','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('5','4','t34','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('5','5','t35','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('11','1','t36','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('5','6','t37','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('4','5','t38','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('2','6','t39','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('1','7','t40','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('12','1','t41','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('10','3','t42','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('11','2','t43','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('13','1','t44','1');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('14','1','t45','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('15','1','t46','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('16','1','t47','2');
Insert into MYTABLE (ID,VERSION,CAPTION,STATUS) values ('17','1','t48','2');
--------------------------------------------------------
--  DDL for Index MYTABLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MYTABLE_PK" ON "MYTABLE" ("ID", "VERSION");
--------------------------------------------------------
--  Constraints for Table MYTABLE
--------------------------------------------------------

  ALTER TABLE "MYTABLE" ADD CONSTRAINT "MYTABLE_PK" PRIMARY KEY ("ID", "VERSION");
  ALTER TABLE "MYTABLE" MODIFY ("VERSION" NOT NULL ENABLE);
  ALTER TABLE "MYTABLE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "MYTABLE" MODIFY ("STATUS" NOT NULL ENABLE);
  ALTER TABLE "MYTABLE" MODIFY ("CAPTION" NOT NULL ENABLE);
  
CREATE OR REPLACE FORCE VIEW "V_MYTABLE_LAST" ("ID", "VERSION", "CAPTION", "STATUS") AS   SELECT  mytable_all.id,  mytable_all.version,  mytable_all.caption,  mytable_all.status FROM mytable mytable_all INNER JOIN (SELECT ID, MAX(version) AS version FROM mytable GROUP BY ID) mytable_last ON  mytable_all.id = mytable_last.id AND mytable_all.version = mytable_last.version;
