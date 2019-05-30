# Stadium
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/wc2018/stadiums.csv'
INTO TABLE stadiums
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

# Teams / Country
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/wc2018/teams.csv'
INTO TABLE teams
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

# Rosters / Players
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/wc2018/rosters.csv'
INTO TABLE players
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

# Matches / Games
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/wc2018/matches.csv'
INTO TABLE matches
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
(GID, GType, @date_var, SID, Team1, Team2, Team1Score, Team2Score)
set GDate = str_to_date(@date_var, '%c/%e/%Y');

# Cards
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/wc2018/cards.csv'
INTO TABLE cards
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

# Goals
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/wc2018/goals.csv'
INTO TABLE goals
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

# Substitutes
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/wc2018/subs.csv'
INTO TABLE subs
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

# Starting lineups
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/wc2018/lineups.csv'
INTO TABLE starters
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

# Own Goals
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/wc2018/own_goals.csv'
INTO TABLE own_goals
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';