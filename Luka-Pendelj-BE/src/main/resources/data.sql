/*
SQLyog Community
MySQL - 8.0.22 : Database - medical-system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`medical-system` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_croatian_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `medical-system`;

/*Data for the table `gender` */

insert  into `gender`(`gender_code`,`gender_name`) values 
('f','female'),
('m','male'),
('o','other'),
('u','unknown');



/*Data for the table `service_type` */

insert  into `service_type`(`service_id`,`service_name`) values 
(1,'Aged Residential Care'),
(2,'Acupuncture'),
(3,'Bowen Therapy'),
(4,'Blood Donation'),
(5,'Immunization'),
(6,'Physiotherapy'),
(7,'Dental'),
(8,'Emergency Medical'),
(9,'Psychology'),
(10,'Dermatology'),
(11,'Home Care/Housekeeping Assistance'),
(12,'Family Planning'),
(13,'Optometry'),
(14,'Osteopathy'),
(15,'Podiatry'),
(16,'Endodontic'),
(17,'Oral Surgery'),
(18,'Osteopathy');

/*Data for the table `organization_type` */

insert  into `organization_type`(`organization_type_id`,`name`) values 
(1,'Hospital'),
(2,'Insurance Company'),
(3,'Educational Institute'),
(4,'Clinical Research'),
(5,'Other');

/*Data for the table `organization` */

insert  into `organization`(`organization_id`,`active`,`address`,`email`,`name`,`organization_code`,`phone`,`organization_type_id`) values 
(1,'','Boulevard 1','medical@mail.com','Medical Lab','ORG-1255','011-2222-999',1),
(2,'','Boulevard 12','biochemical@mail.com','Biochemical','ORG-4444','011-333-2222',4),
(3,'','Square Five 87','University@unimail.com','Medical University','ORG-12121','011-2323-211',3),
(4,'','Hospital street 2','lab@mail.com','LabMedic','ORG-3232','011-3232-211',5),
(5,'','Science Boulevard 32','clinical@mail.com','Clinical New','ORG-56565','021-232-342',4),
(6,'','Healthy Street 34','lab45@mail.com','Lab 45','ORG-2187','011-4533-333',5),
(7,'','Health Street 22','general@mail.com','General Hospital','ORG-23232','011-2324-777',1),
(8,'','Finance Boulevard 121','bigbank@mail.com','Big Bank','ORG-67676','032-1112-232',2),
(9,'','Great Street 32','comp2@mail.com','Company Two','ORG-3382','021-3333-353',3),
(10,'','Boulevard 44','hospital2@mail.com','Childrens hospital','ORG-4545','011-4343-343',1),
(28,'','','biomedical@mail.com','Bio Medical','ORG-99A4','011-999-7122',5),
(29,'','Street 125','logic@mail.com','Medical Logic','ORG-00871','011-872-2222',3);



/*Data for the table `medic` */

insert  into `medic`(`medic_id`,`active`,`address`,`birth_date`,`email`,`firstname`,`lastname`,`medic_code`,`phone`,`qualification`,`gender_code`,`organization_id`) values 
(9,'','Street 34','1996-11-23','pera@mail.com','Petar','Ivanovic','MED-12445','011-2991-122','Medical Assistant','m',1),
(10,'','Boulevard of Glory','1980-09-30','mica@mail.com','Martina','Pejic','MED-8989','011-9989-212','Doctor of Medicine','f',6),
(11,'','Street 21','1977-11-24','misa@mail.com','Misa','Misic','MED-54354','011-2324-434','Medical Assistant','m',2),
(12,'','Street 54','1966-12-12','bilja@mail.com','Biljana','Jovanovic','MED-9989','','Doctor of Medicine','f',2),
(13,'','Funny Street 85','1977-09-08','petra@newmail.com','Petra','Dragovic','MED-99090','011-9892-111','Certified Nurse Midwife','f',3),
(14,'','Boulevard of Justice 452','1988-08-07','mirko@mail.com','Mirko','Peric','MED-95656','021-8979-141','Doctor of Medicine','m',3),
(15,'','Street 68','2003-10-31','jovana@mail.com','Jovana','Milenkovic','MED-43333','011-989-2222','Emergency Medical Technician','o',4),
(16,'','Street 34','2000-09-21','dragan@mail.com','Dragan','Petrovic','MED-99808','011-6767-282','Certified Nurse Midwife','m',4),
(17,'','Boulevard 178','1977-12-10','zeljko@mail.com','Zeljko','Jovanovic','MED-34390','091-2323','Doctor of Medicine','m',5),
(18,'','New Street 899','1971-11-28','dejanar@mail.com','Dejana','Radojicic','MED-6775','033-8989-666','Doctor of Medicine','f',5),
(19,'','Street One 32','1997-07-19','dzeki@mail.com','Dzenan','Muhovic','MED-90121','011-9282-888','Doctor of Medicine','m',6),
(20,'','Street 65','1999-11-09','bojana@mail.com','Bojana','Milakovic','MED-787221','011-999-829','Nurse Practitioner','o',6),
(21,'','Boulevard 12','1990-01-18','dragana@mail.com','Dragana','Peric','MED-09097','011-6927-717','Doctor of Medicine','f',6),
(22,'','Street 25','2001-09-08',NULL,'Jana','Ivanic','MED-29921','011-4927-333','Doctor of Medicine','f',1),
(23,'','Street 98','1965-11-09','joca@mail.com','Jovan','Balic','MED-93301','011-8987-555','Doctor of Medicine','m',7),
(24,'','Boulevard 78','1981-11-06','deki@mail.com','Dejan','Dragovic','MED-09092','011-9111-565','Doctor of Pharmacy','o',7),
(25,'','Street 32','1973-05-16','mile@mail.com','Milenko','Miskovic','MED-30001','011-2231-727','Doctor of Medicine','m',8),
(26,'','Street 98','1992-12-08','nina@mail.com','Nina','Miskovic','MED-00971','091-8881-001','Doctor of Pharmacy','f',8),
(27,'','Boulevard 21','1999-10-21','biljabilja@mail.com','Biljana','Milic','MED-22041','092-022211','Certified Nurse Midwife','u',9),
(34,'','Street 98','1991-12-01','djole@mail.com','Djordje','Jovic','MED-3223','011-8787-222','Doctor of Medicine','o',28);





/*Data for the table `patient` */

insert  into `patient`(`patient_id`,`active`,`address`,`birth_date`,`deceased`,`email`,`firstname`,`lastname`,`marital_status`,`patient_code`,`phone`,`gender_code`,`medic_id`,`organization_id`) values 
(30,'','','1998-12-09','\0','peca@mail.com','Peca','Petrovic','Never Married','PAT-09092','011-8889-221','m',NULL,9),
(31,'','New Address 3','1993-03-31','\0','ivana@mail.com','Ivana','Brajovic','Never Married','PAT-82811','011-783-479','f',NULL,28),
(32,'','Street 91','1944-10-04','\0','jeca@mail.com','Jelena','Dragovic','Widowed','PAT-00320','011-7277-223','o',19,6),
(33,'',NULL,'1950-10-08','\0',NULL,'Drasko','Ivovic','Divorced','PAT-88721','011-8892-222','m',NULL,9),
(35,'','Square 21','2000-10-11','\0','dala@mail.com','Dalila','Mihajlovic','Never Married','PAT-8810','','f',34,28),
(36,'','Street 42','1982-08-01','\0','sani@mail.com','Sanela','Milanovic','Polygamous','PAT-8728','072-888-0202','f',NULL,29),
(37,'','Street 52','2001-06-14','\0','bane@mail.com','Branko','Popovic','Unknown','PAT-788211','','m',18,5),
(38,'','Street 11','1933-05-28','\0','brigi@mail.com','Brigita','Bojovic','Widowed','PAT-989211','011-800-521','f',14,3),
(39,'','Street 91','1944-09-24','','','Dragomir','Ivanovic','Annulled','PAT-77801','018-223-433','o',12,2),
(40,'','Street 24','1963-11-20','\0','panta@mail.com','Panta','Dragovic','Never Married','PAT-98A22','011-9902-221','m',23,7),
(41,'','Street 88','1977-11-04','\0','mili@mail.com','Milovan','Pajic','Married','PAT-110921','011-9982-211','m',NULL,5),
(42,'',NULL,'1995-12-08','\0','bane@mail.com','Branimir','Balic','Unknown','PAT-23242',NULL,'m',NULL,4),
(43,'','Street New 21','1990-11-08','\0','peral@mail.com','Petar','Lukovac','Never Married','PAT-77811','011-991191','u',17,5),
(44,'','Street 91','2010-10-08','\0','vanja@mail.com','Vanja','Peric','Never Married','PAT-11201','092-22124','f',25,8),
(45,'','Street 11','1981-11-10','\0','mail@mail.com','Ana','Misic','Married','PAT-0090',NULL,'f',12,2),
(46,'','Street 46','2009-09-30','\0','brana@mail.com','Brana','Bojovic','Never Married','PAT-450010','011-600201','f',14,3),
(55,'','Street 38','2016-12-01','\0',NULL,'Misa','Misic','Never Married','PAT-87811','069-878212','m',NULL,10),
(56,'','Street 51','2017-10-01','\0','anamala@mail.com','Ana','Minic','Never Married','PAT-676121','011-898-212','f',NULL,1);

/*Data for the table `examination` */

insert  into `examination`(`examination_id`,`diagnosis`,`end_date`,`examination_code`,`priority`,`start_date`,`status`,`organization_id`,`patient_id`,`service_id`) values 
(47,'flu','2020-11-21 01:00:00','EXA-98991','callback results','2020-10-11 02:00:00','triaged',2,39,2),
(48,'flu','2022-06-02 02:00:00','EXA-08810','asap','2022-06-01 02:00:00','in-progress',7,40,1),
(49,'Asthma','2022-07-31 02:00:00','EXA-89111','routine','2022-07-28 02:00:00','planned',2,NULL,1),
(50,NULL,'2020-10-11 02:00:00','EXA-54541','callback results','2020-10-10 02:00:00','cancelled',7,40,2),
(51,'Common cold','2021-12-14 01:00:00','EXA-887066','routine','2021-12-12 01:00:00','finished',5,NULL,16),
(52,'Osteoporosis','2017-12-31 01:00:00','EXA-98981','callback results','2017-10-08 02:00:00','triaged',28,31,14),
(53,'Some Diagnosis','2021-05-11 02:00:00','EXA-3211','routine','2021-05-10 02:00:00','cancelled',2,39,10),
(57,'Ekcem','2021-10-11 02:00:00','EXA-1256','routine','2021-10-10 02:00:00','in-progress',10,55,3),
(58,'COVID','2022-06-07 02:00:00','EXA-977','asap','2022-06-06 02:00:00','entered-in-error',28,31,5),
(59,NULL,'2019-09-10 02:00:00','EXA-123444','callback results','2019-09-09 02:00:00','triaged',3,NULL,8),
(60,'Asthma','2018-09-13 02:00:00','EXA-34155','routine','2018-09-12 02:00:00','in-progress',3,NULL,2),
(61,'Epilepsy','2020-11-10 01:00:00','EXA-8921','routine','2020-10-10 02:00:00','suspended',6,NULL,4),
(62,'','2019-12-11 01:00:00','EXA-01010','rush','2019-12-10 01:00:00','triaged',1,NULL,1),
(63,'','2011-10-14 02:00:00','EXA-0820','callback results','2011-09-12 02:00:00','triaged',1,NULL,7);

/*Data for the table `examination_medic` */

insert  into `examination_medic`(`examination_id`,`medic_id`) values 
(47,11),
(53,11),
(47,12),
(48,23),
(50,23),
(48,24),
(52,34);



/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values 
(64);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
