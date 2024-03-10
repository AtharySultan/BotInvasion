CREATE SCHEMA APproj;

CREATE TABLE APproj.USER_INFO(
userID INT (10) NOT NULL,
userName VARCHAR (100),
userEmail VARCHAR (100),
userPassword VARCHAR (100),
CONSTRAINT userID_PK PRIMARY KEY (userID)
);

CREATE TABLE APproj.LEVELS(
levelID INT (10) NOT NULL,
levelName VARCHAR (100),
numOfPuzzles INT (10) ,
numOfTokens INT (10),
collectedTKNs INT (10),
CONSTRAINT levelID_PK PRIMARY KEY (levelID)
);

CREATE TABLE APproj.score(
userID INT (10) NOT NULL,
levelID INT (10) NOT NULL,
collectedTokens INT(10),
completedLvl INT(10),
prize char(1)CHECK (prize IN("YES","NO")),
CONSTRAINT score_PK PRIMARY KEY (userID,levelID),
CONSTRAINT score_FK1 FOREIGN KEY (userID) REFERENCES USER_INFO(userID) ON DELETE CASCADE,
CONSTRAINT score_FK2 FOREIGN KEY (levelID) REFERENCES LEVELS(levelID) ON DELETE CASCADE
);

CREATE TABLE APproj.PUZZLE(
puzzlID INT (10) NOT NULL,
levelID INT (10),
PuzzlelName VARCHAR (100),
PuzzlesSolution VARCHAR (50),
CONSTRAINT PUZZLE_PK PRIMARY KEY (puzzlID),
CONSTRAINT PUZZLE_FK1 FOREIGN KEY (levelID) REFERENCES LEVELS(levelID) ON DELETE CASCADE
);

INSERT INTO LEVELS
VALUES (1, 'Level 1',3,3,null),
       (2, 'Level 2',3,3,null),
	   (3, 'Level 3',3,4,null);
       
INSERT INTO PUZZLE
VALUES (11,1,'ComputerPuzzle','8'),
       (21,1,'FilesPuzzle','2'),
	   (31,1,'boardPuzzle','6'),
       
       (12,2,'gussPuzzle',null),
       (22,2,'memoryGame',null),
       (32,2,'BejeweledApp',null),
       
       (13,3,'DatePuzzle','2023-03-05'),
       (23,3,'FlaskPuzzle',null),
       (33,3,'WaterPuzzle',null);
       
UPDATE LEVELS SET collectedTKNs = 0 ; 
ALTER TABLE score MODIFY COLUMN prize CHAR(3);

DELETE FROM score;
DELETE FROM USER_INFO;

      