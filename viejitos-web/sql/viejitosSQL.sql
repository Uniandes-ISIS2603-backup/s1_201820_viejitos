
delete from VIEJITOENTITY;
delete  from FRANJAHORARIAENTITY;
delete  from  CALENDARIOSEMANALENTITY;
delete from    QUEJAENTITY;
delete from PAGOENTITY;
delete from MEDICOENTITY_HISTORIACLINICAENTITY;
delete from HISTORIACLINICAENTITY;
delete from  FACTURAENTITY;
delete from  CLIENTEENTITY_CALIFICACIONENTITY;
delete from  CLIENTEENTITY_ENFERMEROENTITY;
delete from   CALIFICACION ENTITY;
delete from   CITAENTITY;
DELETE FROM ENFERMEROENTITY;



insert into APP.CLIENTEENTITY (ID,CONTRASENA,ESTADO,LOGIN,NAME,TIPO) VALUES(5,'222',2,'testA','testA',1);

INSERT INTO APP.CALENDARIOSEMANALENTITY (ID) VALUES(1 );
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(1,'LUNES',0900,0700,0,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(2,'MARTES',0900,0700,1,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(3,'MIERCOLES',0900,0700,1,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(4,'JUEVES',0900,0700,1,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(5,'VIERNES',0900,0700,0,1);



INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(6,'LUNES',1100,0900,0,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(7,'MARTES',1100,0900,1,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(8,'MIERCOLES',1100,0900,1,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(9,'JUEVES',1100,0900,0,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(10,'VIERNES',1100,0900,1,1);



INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(11,'LUNES',1300,1100,1,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(12,'MARTES',1300,1100,1,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(13,'MIERCOLES',1300,1100,0,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(14,'JUEVES',1300,1100,0,1);
INSERT INTO APP.FRANJAHORARIAENTITY(ID,DIASEMANA,HORAFIN,HORAINICIO,OCUPADO,CALENDARIO_ID) VALUES(15,'VIERNES',1300,1100,0,1);




INSERT INTO APP.ENFERMEROENTITY(ID,CONTRASENIA,CV,LOGIN,NAME,TIPO,CALENDARIO_ID) VALUES(6,'espitia','UN BACAN','espitia','espitia',2,1);