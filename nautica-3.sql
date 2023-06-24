
create database if not exists nautica;

use nautica;


create table if not exists Clientes(
IDCliente int auto_increment primary key,
Nombre varchar(30),
Correo varchar(50),
Telefono varchar(15),
Estado boolean 
);

select * from Clientes;

create table if not exists Barco(
IDBarco int auto_increment primary key,
NOMBRE varchar(30),
Capitan varchar(30),
tamaño int,
Estado boolean,
fk_IDCliente int,
constraint fk_cliente_barco 
foreign key(fk_IDCliente)
references Clientes(IDCliente),
Foto varchar(255),
FechaSalidaEstimada date
);

create table if not exists FolioPago(
IDFolio int auto_increment primary key,
Concepto varchar(50),
Monto double,
fk_Cliente int,
constraint fk_FolioPago_cliente 
foreign key(fk_Cliente)
references Clientes(IDCliente),
FechaPago date
);

create table if not exists EstadoCuenta(
IDEstadoCuenta int auto_increment primary key,
fk_Cliente int,
constraint fk_EstadoCuenta_clientes 
foreign key(fk_Cliente)
references Clientes(IDCliente),
Credito double
);

create table if not exists Muelle(
IDMuelle int auto_increment primary key,
Disponibilidad boolean,
Tamaño int,
fk_Barco int,
constraint fk_Muelle_barco
foreign key(fk_Barco)
references Barco(IDBarco)
);



create table if not exists DiqueSeco(
IDDiqueSeco int auto_increment primary key,
Disponibilidad boolean,
Tamaño int,
fk_barcos int,
constraint fk_DiqueSeco_barco
foreign key(fk_barcos)
references Barco(IDBarco)
);

create table if not exists Casetas(
IDCaseta int auto_increment primary key,
Tamaño int,
fk_Cliente int,
constraint fk_Caseta_Cliente
foreign key(fk_Cliente)
references Clientes(IDCliente),
Disponibilidad boolean
);

create table if not exists Empleados(
IdEmpleados int primary key auto_increment,
nombre varchar(50),
telefono varchar(15),
foto varchar(255),
Estado boolean
);



create table if not exists Entrada(
IdEntrada int primary key auto_increment,
fkEmpleado int,
constraint fk_Empleado_Entrada
foreign key(fkEmpleado)
references Empleados(IdEmpleados),
fkBarco int,
constraint fk_Empleado_Barco
foreign key(fkBarco)
references Empleados(IdEmpleados),
Lugar varchar(20),
Fecha date
);

create table if not exists Salida(
IdSalida int primary key auto_increment,
fkEmpleados int,
constraint fk_Empleado_Salida
foreign key(fkEmpleados)
references Empleados(IdEmpleados),
fkBarcos int,
constraint fk_Empleado_Barcos
foreign key(fkBarcos)
references Empleados(IdEmpleados),
Lugar varchar(20),
Fecha date
);

insert into Clientes (IDCliente, Nombre, Correo, Telefono, Estado)
values(0,"Ruy Luna","2020030219@gmail.com","6691470985", true),(0,"Jese Sandoval","2020030220@gmail.com","6692487501", true),(0,"Pedro Gonzales","2020030222@gmail.com","6692498769", true);

insert into Clientes (IDCliente, Nombre, Correo, Telefono, Estado)
values(4,"Erick Lopez","erick@gmail.com","6691353423", true),
(5,"Jeanick Gonzalez","jeanick@gmail.com","6691347644", true),
(6,"Antonio Salas","antonio@gmail.com","6691234234", true),
(7,"Jesus Tostado","jesus@gmail.com","6691543345", true),
(8,"Israel Jimenez","Israel@gmail.com","6691765234", true),
(9,"Juan Moreno","juan@gmail.com","6691321232", true),
(10,"Carlos Lopez","carlos@gmail.com","6691958675", true),
(11,"Pedro Lopez","pedrol@gmail.com","6691574832", true),
(12,"Israel Lopez","israello@gmail.com","6691234654", true),
(13,"Ruy Lopez","ruyl@gmail.com","6691987567", true),
(14,"Juan Lopez","juanl@gmail.com","6691543234", true),
(15,"Jese Luna","jesel@gmail.com","6692498312", true),
(0,"Gael","Gael@gmail.com","6692489311",true),
(0,"Sergio","Sergio@hotmail.com","6692489312",true),
(0,"Nicolas","nico@gmail.com","6692489313",true),
(0,"Dylan","dyfalco@gmail.com","6692489314",true),
(0,"Gabriel","gaby@gmail.com","6692489315",true),
(0,"Jorge","georgemx@gmail.com","6692489316",true),
(0,"José","jose@gmail.com","6692489317",true),
(0,"Adam","adam@gmail.com","6692489318",true),
(0,"Liam","liam@gmail.com","6692489319",true),
(0,"Erick Amir","eamir@gmail.com","6692489321",true);

insert into Barco (IDBarco, NOMBRE, fk_IDCliente, Capitan, Tamaño, Estado, FechaSalidaEstimada)
values(0,"NACEB",1,"Juan Carlos",110, true,"2022/09/10"),
(0,"RAZER",2,"Jesus de Israel",120, true,"2022/09/11"),
(0,"ASUS",3,"Erick Jeanick",150, true,"2022/09/12"),
(0, "Aurora",4,"Hugo",150, true,"2022/09/13"),
(0, "Catamaran", 1, "Martín",140,true,"2022/10/20"),
(0, "Stacy",2,"Lucas",150,true,"2023/02/10"),
(0, "FAKER",3,"Mateo",150,true,"2022/09/10"),
(0, "LonelyWife",4,"Leo",150,true,"2022/09/20"),
(0, "4DAM",5,"Daniel",150,true,NULL),
(0, "WUNDER",6,"Alejandro",150,true,"2022/09/30"),
(0, "Thyak",7,"Pablo",150,true,"2023/08/10"),
(0, "Oddie",8,"Manuel",150,true,"2023/08/02"),
(0, "Grell",9,"Álvaro",150,true,"2023/08/03"),
(0, "Armut",10,"Adrián",150,true,"2023/08/11"),
(0, "Nagzet",11,"Álvaro",150,true,"2023/08/10"),
(0, "Mazino",12,"Adrián",150,true,"2023/08/14"),
(0, "Keznit",13,"David",150,true,"2023/08/10"),
(0, "Delzik",14,"Mario",150,true,"2023/08/16"),
(0, "Klaus",15,"Enzo",150,true,"2023/08/17"),
(0, "Tenz",14,"David Antonio",150,true,"2023/08/18"),
(0, "S1mple",13,"Diego Alberto",150,true,"2023/08/19"),
(0, "Hiko",12,"Marcos Izan",150,true,"2023/08/20"),
(0, "Chovy",12,"Agustin de Iturbide",150,true,"2023/08/10"),
(0, "Ruler",11,"Benito Juarez",150,true,"2023/08/10"),
(0, "Gumayusi",10,"Marc Gonzales",150,true,"2023/08/10"),
(0, "Clozer",5,"Gonzale Gael",150,true,"2023/08/10"),
(0, "Keria",6,"Sergio ",150,true,"2023/08/10"),
(0, "Targamas",7,"Thiago",150,true,"2023/08/10"),
(0, "Rekkles",8,"Oliver Jose",150,true,"2023/08/10");

insert into Muelle (IDMuelle, Disponibilidad, Tamaño, fk_Barco)
values(0,false,200,1),
(0,false, 200, 2),
(0,false, 200,3),
(0,false, 200,4),
(0,false, 200,5),
(0,false, 200,6),
(0,false, 200,7),
(0,false, 200,8),
(0,false, 200,9),
(0,false, 200,10),
(0,false, 200,11),
(0,false, 200,12),
(0,false, 200,13),
(0,false, 200,14),
(0,true, 200,null),
(0,false, 200,16),
(0,false, 200,17),
(0,false, 200,18),
(0,true, 200,null),
(0,false, 200,19),
(0,false, 200,20),
(0,false, 200,21),
(0,false, 200,22);

insert into DiqueSeco (IDDiqueSeco, Disponibilidad, Tamaño, fk_barcos)
values(0,false,200,15),(0,false,200,23),(0,false,200,24),(0,false,200,25),
(0,false,200,26),
(0,false,200,27),
(0,false,200,28),
(0,false,200,29),
(0,true,200,null),
(0,true,200,null);

insert into Casetas (IDCaseta, Tamaño, fk_Cliente, Disponibilidad)
values(0,100,NULL,true),(0,100,NULL,true),(0,100,10,false),
(0,100,16,false),
(0,100,17,false),
(0,100,18,false),
(0,100,19,false),
(0,100,20,false),
(0,100,21,false),
(0,100,22,false);


insert into FolioPago (IDFolio, Concepto, Monto, fk_Cliente, FechaPago)
values(0,"Dry dock",2000,1,"2022/05/24"),
(0,"Muelle",300,2,"2022/05/23"),
(0,"Caseta",600,3,"2022/05/23"),
(0,"Muelle",700,4,"2022/06/03"),
(0,"Caseta",2000,5,"2022/06/07"),
(0,"Dry dock",1000,6,"2022/06/10"),
(0,"Muelle",7000,7,"2022/06/13"),
(0,"Caseta",5500,8,"2022/06/15"),
(0,"Caseta",2400,9,"2022/06/20"),
(0,"Caseta",1300,10,"2022/06/21"),
(0,"Muelle",3300,11,"2022/06/27"),
(0,"Dry Dock",2300,12,"2022/06/30"),
(0,"Muelle",1400,13,"2022/07/04"),
(0,"Muelle",900,14,"2022/07/08"),
(0,"Caseta",1450,15,"2022/07/11"),
(0,"Dry Dock",2340,1,"2022/07/12"),
(0,"Dry Dock",6500,2,"2022/07/20"),
(0,"Muelle",6600,3,"2022/07/22"),
(0,"Muelle",7600,4,"2022/07/26"),
(0,"Muelle",5300,5,"2022/07/27");

#IdEmpleados, nombre, telefono, foto, estado
insert into Empleados (IdEmpleados, Nombre, Telefono, Foto, Estado)
values(0,"Maguan","6698583921","Aqui va la dirección de la foto",true),
(0,"Marquitos","6666666661","Aqui va la dirección de la foto", true),
(0,"Carlitos","6666666662","Aqui va la dirección de la foto", true),
(0,"Lizbeth","6666666663","Aqui va la dirección de la foto", true),
(0,"Isrrrrrael","6666666664","Aqui va la dirección de la foto", true),
(0,"Carlos Garcia","6666666665","Aqui va la dirección de la foto", true),
(0,"Luis Angel","6666666666","Aqui va la dirección de la foto", true),
(0,"Brian Eduardo","6666666667","Aqui va la dirección de la foto", true),
(0,"El memo","6666666668","Aqui va la dirección de la foto", true),
(0,"El purga","6666666669","Aqui va la dirección de la foto", true);

#IdEntrada, fkEmpleado, fkBarco, Lugar, Fecha
insert into Entrada(IdEntrada, fkEmpleado, fkBarco, Lugar, Fecha)
values(0, 1, 1, "Muelle", "2022/07/24"),
(0, 2, 1, "Muelle", "2022/07/26"),
(0, 3, 1, "Muelle", "2022/07/27"),
(0, 4, 2, "Dry Dock", "2022/07/27"),
(0, 5, 3, "Caseta", "2022/06/07"),
(0, 1, 2, "Muelle", "2022/06/10"),
(0, 6, 3, "Muelle", "2022/06/11"),
(0, 7, 1, "Muelle", "2022/06/13"),
(0, 9, 1, "Muelle", "2022/06/15"),
(0, 10, 2, "Muelle", "2022/06/17"),
(0, 3, 2, "Muelle", "2022/06/19"),
(0, 8, 3, "Muelle", "2022/06/21");

#IdSalida, fkEmpleados, fkBarcos, Lugar, Fecha
insert into Salida(IdSalida, fkEmpleados, fkBarcos, Lugar, Fecha)
values(0, 2, 1, "Muelle", "2022/07/24"),
(0, 1, 1, "Muelle", "2022/07/26"),
(0, 7, 1, "Muelle", "2022/07/27"),
(0, 5, 2, "Dry Dock", "2022/07/27"),
(0, 3, 3, "Caseta", "2022/06/07"),
(0, 2, 2, "Muelle", "2022/06/10"),
(0, 7, 3, "Muelle", "2022/06/11"),
(0, 8, 1, "Muelle", "2022/06/13"),
(0, 10, 1, "Muelle", "2022/06/15"),
(0, 1, 2, "Muelle", "2022/06/17"),
(0, 6, 2, "Muelle", "2022/06/19"),
(0, 4, 3, "Muelle", "2022/06/21");

insert into EstadoCuenta (IDEstadoCuenta, fk_Cliente, Credito)
values(1,1,2000.10),(2,2,350.25),(3,3,600.0);



-- Corte de caja de ventas 1 día
CREATE PROCEDURE corteCajaDia(in fecha date)
    select sum(monto) from foliopago where FechaPago=fecha;
;

-- Corte de caja de ventas 1 mes
CREATE PROCEDURE corteCajaMes(in fecha int(2))
    select sum(monto) from foliopago where month(FechaPago)=fecha;
;

-- Comparativa de ventas contra compras
CREATE PROCEDURE verClienteEstado(in nombre varchar(64))
    select * from clientes inner join EstadoCuenta on clientes.idCliente = EstadoCuenta.fk_Cliente where clientes.nombre = nombre;
;

-- Corte de caja de compras por mes
-- CREATE PROCEDURE no(in * *) ;

-- Producto mas vendido
CREATE PROCEDURE productoMasVendido(in masVendido int)
    select IDFolio, Concepto, Monto, fk_Cliente, FechaPago from foliopago order by monto desc;
;

-- Métodos libres

CREATE PROCEDURE verClientes()
    select * from clientes;
;


CREATE PROCEDURE insertarCliente(in nb varchar(100), in cor VARCHAR(100), in tel VARCHAR(100))
    insert into clientes (IDCliente,Nombre,Correo,Telefono)values(null,nb, cor,tel);
;


CREATE PROCEDURE borrarCliente(in id int)
    delete from clientes where IDCliente=id;
;

CREATE PROCEDURE editarMuelle(in id int, in disp boolean, in tam int, in fk int)
	update muelle Set Disponibilidad = disp, Tamaño = tam, fk_Barco = fk where IDMuelle = id;
;

CREATE PROCEDURE editarDique(in id int, in disp boolean, in tam int, in fk int)
	update DiqueSeco Set Disponibilidad = disp, Tamaño = tam, fk_Barcos = fk where IDDiqueSeco = id;
;

CREATE PROCEDURE editarCaseta(in id int, in disp boolean, in tam int, in fk int)
	update Casetas Set Disponibilidad = disp, Tamaño = tam, fk_Cliente = fk where IDCaseta = id;
;


CREATE PROCEDURE verBarco()
    select IDBarco, barco.Nombre as "Nombre de la embarcación", Capitan, Tamaño as "Tamaño en metros cuadrados", barco.Estado, clientes.nombre as "Nombre del cliente", foto, FechaSalidaEstimada as "Fecha estimada de salida" from barco inner join clientes on barco.fk_IDCliente = clientes.IDCliente;
;


CREATE PROCEDURE verCasetas()
    select IDCaseta as "ID de la caseta", tamaño, clientes.nombre as "Nombre del cliente", disponibilidad as "Disponibilidad. \n1 es Disponible \n0 es Ocupado" from casetas inner join clientes on casetas.fk_Cliente = clientes.IDCliente;
;

CREATE PROCEDURE verDiqueseco()
    select IDDiqueSeco, Disponibilidad, DiqueSeco.Tamaño as "Tamaño del dique seco", barco.Nombre as "Nombre de la embarcación" from diqueseco inner join barco on DiqueSeco.fk_barcos=barco.IDBarco;
;
call verDiqueSeco();
CREATE PROCEDURE verEmpleados()
    select * from empleados;
;

CREATE PROCEDURE verEntrada()
    select IdEntrada as "ID de la entrada", empleados.nombre as "Nombre del empleado", barco.nombre as "Nombre de la embarcación", lugar, fecha from Entrada inner join empleados on Entrada.fkEmpleado = empleados.IdEmpleados inner join barco on Entrada.fkBarco = barco.IdBarco;
;


CREATE PROCEDURE verEstadoCuenta()
    select IDEstadoCuenta, clientes.nombre, credito from estadocuenta inner join clientes on estadocuenta.fk_cliente = clientes.IDCliente;
;



CREATE PROCEDURE verFolioPago()
    select IDFolio, Concepto, Monto, clientes.Nombre, FechaPago, estadocuenta.IDEstadoCuenta as "ID del estado de cuenta" from foliopago inner join clientes on foliopago.fk_Cliente = clientes.IDCliente inner join estadocuenta on estadocuenta.fk_Cliente = clientes.IDCliente;
;



CREATE PROCEDURE verMuelle()
    select IDMuelle, Disponibilidad, Muelle.Tamaño, barco.nombre from muelle inner join barco on muelle.fk_Barco=barco.IDBarco;
;

CREATE PROCEDURE verSalida()
    select IdSalida as "ID de la salida", empleados.nombre as "Nombre del Empleado", barco.nombre as "Nombre de la embarcación", lugar, fecha from Salida inner join empleados on Salida.fkEmpleados = empleados.IdEmpleados inner join barco on Salida.fkBarcos = barco.IdBarco;
;

-- Estado de cuenta

CREATE PROCEDURE foraneoEstadoCuentaClientes()
    select nombre from clientes inner join EstadoCuenta on clientes.idCliente = EstadoCuenta.fk_Cliente where clientes.nombre = nombre;
;

CREATE PROCEDURE foraneoEstadoCuentaFolioPago()
select idFolio from foliopago inner join EstadoCuenta on foliopago.idFolio = EstadoCuenta.fk_IDFolios where foliopago.idFolio = idFolio;
;
-- FolioPago

CREATE PROCEDURE foraneoFolioPagoClientes()
select nombre from clientes inner join foliopago on clientes.idCliente = foliopago.fk_Cliente where clientes.nombre = nombre;
;
CREATE PROCEDURE foraneoFolioPagoClientesId()
select nombre from clientes inner join foliopago on clientes.idCliente = foliopago.fk_Cliente where clientes.nombre = nombre;
;
-- Caseta
CREATE PROCEDURE foraneoCasetasClientes()
select nombre from clientes inner join casetas on clientes.idCliente = casetas.fk_Cliente where clientes.nombre = nombre;
;
CREATE PROCEDURE foraneoCasetasClientesId()
select idCliente from clientes inner join casetas on clientes.idCliente = casetas.fk_Cliente where clientes.nombre = nombre;
;
-- Diqueseco
CREATE PROCEDURE foraneoDiqueSecoBarco()
select NOMBRE from barco inner join diqueseco on barco.IDBarco = diqueseco.fk_Barcos where Barco.NOMBRE = NOMBRE;
;
-- Muelle
CREATE PROCEDURE foraneoMuelleBarco()
select NOMBRE from barco inner join muelle on barco.IDBarco = muelle.fk_Barco where Barco.NOMBRE = NOMBRE;
;
CREATE PROCEDURE foraneoMuelleBarcoId()
select idbarco from barco inner join muelle on barco.IDBarco = muelle.fk_Barco where Barco.NOMBRE = NOMBRE;
;
-- Barco
CREATE PROCEDURE foraneoBarcoClientes()
select clientes.Nombre from clientes inner join barco on clientes.idCliente = barco.fk_IDCliente where clientes.Nombre = clientes.Nombre;
;
-- Entrada
CREATE PROCEDURE foraneoEntradaEmpleados()
select nombre from empleados inner join entrada on empleados.idEmpleados = entrada.fkEmpleado where empleados.nombre = nombre;
;
CREATE PROCEDURE foraneoEntradaBarco()
select NOMBRE from barco inner join entrada on barco.IDBarco = entrada.fkBarco where Barco.NOMBRE = NOMBRE;
;
-- Salida
CREATE PROCEDURE foraneoSalidaEmpleados()
select nombre from empleados inner join salida on empleados.idEmpleados = salida.fkEmpleados where empleados.nombre = nombre;
;

CREATE PROCEDURE foraneoSalidaBarco()
select NOMBRE from barco inner join salida on barco.IDBarco = salida.fkBarcos where Barco.NOMBRE = NOMBRE;
;

CREATE PROCEDURE buscarClienteId(in id int)
	select nombre, correo, telefono from clientes where idCliente=id;
;

CREATE PROCEDURE nombreBarco()
	select nombre from barco;
;

CREATE PROCEDURE nombreClientes()
	select nombre from clientes;
;

CREATE PROCEDURE nombreEmpleados()
	select nombre from empleados;
;
CREATE PROCEDURE idBarco()
	select idBarco from barco;
;

CREATE PROCEDURE idClientes()
	select idCliente from clientes;
;

CREATE PROCEDURE idEmpleados()
	select idEmpleados from empleados;
;

#EDITAR
CREATE PROCEDURE editarBarco(in ID int, in nmbr varchar(30), in cpt varchar(30), in tmño int, in estad boolean, in fk_IDClient int, in photo varchar(255), in FechaSalidaEST date)
	update barco set IDBarco = ID, Nombre = nmbr, capitan = cpt, tamaño = tmño, estado = estad, fk_IDCliente = fk_IDClient, foto = photo, FechaSalidaEstimada = FechaSalidaEST where IDBarco = ID;
;

CREATE PROCEDURE editarEmpleado(in IdEmpleados1 int, in nombre1 varchar(50), in telefono1 varchar(15), in foto1 varchar(255), in estado1 boolean)
	update Empleados set IdEmpleados = IdEmpleados1, nombre = nombre1, telefono = telefono1, foto = foto1, estado = estado1 where IdEmpleados = IdEmpleados1;
;

CREATE PROCEDURE editarEntrada(in id int, in fkEmpled int, in fkBoat int, in lg varchar(50), in feich date)
	update Entrada set IdEntrada = id, FkEmpleado = fkEmpled, fkBarco = fkBoat, Lugar = lg, Fecha = feich where IdEntrada = id;
;

CREATE PROCEDURE editarSalida(in id int, in fkEmpled int, in fkBarco int, in lg varchar(50), in fecha date)
	update Salida set IdSalida = id, FkEmpleado = fkEmpled, fkBarco = fkBoat, Lugar = lg, Fecha = feich where IdSalida = id;
;

CREATE PROCEDURE editarFolioPago(in IDFol int, in Conc int, IN mon double, in fk_clientes int, in Fechap int)
	update FolioPago set IDFolio = IDFol, concepto=conc,monto=mon,fk_Cliente=fk_clientes,FechaPago=Fechap where IDFolioPago = IDFol;
;

CREATE PROCEDURE editarEstadoCuenta(in IDEst int, in fk_cl int, in cred int)
	update EstadoCuenta set IDEstadoCuenta=IDEst, fk_cliente=fk_cl,credito=cred where IDEstado = IDEst;  
;

#INSERTAR
CREATE PROCEDURE insertarBarco(in ID int, in nmbr varchar(30), in cpt varchar(30), in tmño int, in estad boolean, in fk_IDClient int, in photo varchar(255), in FechaSalidaEST date)
	insert into barco values(ID, nmbr, cpt, tmño, estad, fk_IDClient, photo, FechaSalidaEST)
;

CREATE PROCEDURE insertarEmpleado(in IdEmpleados1 int, in nombre1 varchar(50), in telefono1 varchar(15), in foto1 varchar(255), in estado1 boolean)
	insert into  Empleados value(idEmpleados1,nombre1,telefono1,foto1,estado1)
;

CREATE PROCEDURE insertarEntrada(in id int, in fkEmpled int, in fkBoat int, in lg varchar(50), in feich date)
	insert into Entrada values( id, fkEmpled, fkBoat, lg, feich)
;
CREATE PROCEDURE insertarSalida(in id int, in fkEmpled int, in fkBoat int, in lg varchar(50), in feich date)
	insert into Salida values( id, fkEmpled, fkBoat, lg, feich)
;

CREATE PROCEDURE insertarFolioPago(in IDFol int, in Conc int, IN mon double, in fk_clientes int, in Fechap int)
	insert into FolioPago value(IDFol,conc,mon,fk_clientes,Fechap)
;

CREATE PROCEDURE insertarEstadoCuenta(in IDEst int, in fk_cl int, in cred int)
	insert INTO EstadoCuenta value(IDEst,fk_cl,cred) 
;


