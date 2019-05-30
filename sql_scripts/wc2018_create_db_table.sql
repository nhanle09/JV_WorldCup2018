# Creating and selecting database
create database wc2018;
use wc2018;

# Creating tables
# Stadium
create table stadiums(
	SID varchar(3) not null,
    SName varchar(30) not null,
    SCity varchar(20) not null,
    SCapacity int not null,
    primary key (SID) );
    
# Teams or Country
create table teams(
	TID char(2) not null,
    CName varchar(15) not null,
    Continent varchar(20) not null,
    Confederation varchar(10) not null,
    Population int not null,
    primary key (TID), unique (CName) );
    
# Players
create table players(
	CName varchar(15) not null,
    TID char(2) not null,
    PNumber int not null,
    PPosition char(2) not null,
    PName varchar(30) not null,
    PBDate varchar(10),
    PJName varchar(20) not null,
    PClub varchar(40),
    PHeight int not null,
    PWeight int not null,
    primary key (PNumber, TID),
    foreign key (TID) references teams(TID) );

# Games / Matches
create table matches(
    GID char(3) not null,
    GType char(1) not null,
    GDate date not null,
    #GDate varchar(10) not null,
    SID char(3) not null,
    Team1 char(2) not null,
    Team2 char(2) not null,
    Team1Score int not null default 0,
    Team2Score int not null default 0,
    primary key (GID),
    foreign key (SID) references stadiums (SID),
    foreign key (Team1) references teams (TID),
    foreign key (Team2) references teams (TID) );

# Red/Yellow Cards
create table cards(
	GID char(3) not null,
    TID char(2) not null,
    PNumber int not null,
    Color varchar(7),
    CTime int,
    primary key (PNumber, TID, GID, CTime),
    foreign key (PNumber, TID) references players (PNumber, TID),
    foreign key (GID) references matches (GID) );
    
# Goals
create table goals(
	GID char(3) not null,
    TID char(2) not null,
	PNumber int not null,
    GTime int,
    Penalty char(1),
    primary key (PNumber, TID, GID, GTime),
    foreign key (PNumber, TID) references players (PNumber, TID),
    foreign key (GID) references matches (GID) );
    
# Substitutes
create table subs(
	GID char(3) not null,
    TID char(2) not null,
    PNumberIn int not null,
    PPosition char(2) not null,
    PNumberOut int not null,
    STime int,
    primary key (PNumberIn, TID, PNumberOut, GID), 
    foreign key (PNumberIn, TID) references players (PNumber, TID),
    foreign key (PNumberOut, TID) references players (PNumber, TID),
    foreign key (GID) references matches (GID) );
    
# Starting Lineup
create table starters(
	GID char(3) not null,
    TID char(2) not null,
    PNumber int not null,
    primary key (PNumber, GID, TID),
    foreign key (PNumber, TID) references players (PNumber, TID),
    foreign key (GID) references matches (GID) );
    
# Goals against own team
create table own_goals(
	GID char(3) not null,
    TID char(2) not null,
    PNumber int not null,
    GTime int,
    ForTID char(2) not null,
    primary key (GID, TID, PNumber, GTime, ForTID),
    foreign key (PNumber, TID) references players (PNumber, TID),
    foreign key (PNumber, ForTID) references players (PNumber, TID),
    foreign key (GID) references matches (GID) );
    
    
	