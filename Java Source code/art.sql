create database ARTGALLERY;
use ARTGALLERY;
show tables;
create table ARTIST(Artist_ID varchar(9) primary key, Artist_Name varchar(20), BirthPlace
varchar(20),DOB date, Artist_PhNo bigint);
create table CUSTOMER(Customer_ID varchar(11) primary key,Customer_Name
varchar(20),Customer_Address varchar(20),Email_ID varchar(20),Customer_PhNo bigint);
create table GALLERY(Gallery_ID varchar(10) primary key,Gallery_Name
varchar(40),Gallery_Location varchar(20),Gallery_PhNo varchar(20));
create table CATEGORY(Category_ID varchar(11) primary key, Cat_Name varchar(20));
create table MANAGER(Manager_ID varchar(10) primary key,Manager_Name
varchar(20),Manager_PhNo bigint,Startdate date,Salary int,Gender varchar(6), Category_ID
varchar(11),Gallery_ID varchar(10),foreign key(Category_ID) references CATEGORY(Category_ID)
on delete cascade on update cascade,foreign key(Gallery_ID) references GALLERY(Gallery_ID) on
delete cascade on update cascade);
create table EXHIBITIONS(Exhibition_ID varchar(13) primary key,Date date, Location varchar(20),
time varchar(10),Day varchar(8),RegistrationFee int, NoOfArtworks int);
create table ITEMSPURCHASED(Art_ID varchar(6) primary key,Artist_ID
varchar(9),Artwork_Name varchar(20),Year int, Category_ID varchar(11),Price int);
create table ARTWORK(Art_ID varchar(6) primary key,Artist_ID varchar(9),Artwork_Name
varchar(40),Year int, Category_ID varchar(11),Price int,Gallery_ID varchar(10), Exhibition_ID
varchar(13),foreign key(Artist_ID) references ARTIST(Artist_ID) on delete cascade on update
cascade, foreign key(Category_ID) references CATEGORY(Category_ID) on delete cascade on
update cascade,foreign key(Gallery_ID) references GALLERY(Gallery_ID) on delete cascade on
update cascade,foreign key(Exhibition_ID) references EXHIBITIONS(Exhibition_ID) on delete
cascade on update cascade);

create trigger NoOfArt
After update on Artwork
for each row
update Exhibitions
set NoOfArtworks=NoOfArtworks+0.5
where Exhibition_ID=new.Exhibition_ID and Gallery_ID is not null;


create trigger NoOfArts
After delete on Artwork
for each row
update Exhibitions
set NoOfArtworks= NoOfArtworks-1
where Exhibition_ID=old.Exhibition_ID;

