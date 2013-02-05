-- add a user
INSERT INTO USER (FIRST_NAME, LAST_NAME, EMAIL) values ('Mike', 'L.', 'lanyonm@gmail.com');
INSERT INTO USER (FIRST_NAME, LAST_NAME, EMAIL) values ('Matt', 'R.', 'test@test.com');

-- add some ingredients
INSERT INTO INGREDIENTS (NAME, DESCRIPTION) values ('milk', 'it comes from cows');
INSERT INTO INGREDIENTS (NAME, DESCRIPTION) values ('butter', 'the real deal');
INSERT INTO INGREDIENTS (NAME, DESCRIPTION) values ('margarine', 'can''t believe it''s not butter!');
INSERT INTO INGREDIENTS (NAME, DESCRIPTION) values ('sugar', 'real, sweet, sugar');
INSERT INTO INGREDIENTS (NAME, DESCRIPTION) values ('powdered sugar', 'looks like anthrax, but is much tastier');
INSERT INTO INGREDIENTS (NAME, DESCRIPTION) values ('flour', 'white and powdery');
INSERT INTO INGREDIENTS (NAME, DESCRIPTION) values ('cream cheese', 'what you put on a bagel');
INSERT INTO INGREDIENTS (NAME, DESCRIPTION) values ('Cool Whip', 'imitation whipped cream');
INSERT INTO INGREDIENTS (NAME, DESCRIPTION) values ('instant pudding', 'generic brand 5-minute pudding');
INSERT INTO INGREDIENTS (NAME, DESCRIPTION) values ('pistachios', 'the little green nuts');

-- add some recipe types
INSERT INTO RECIPE_TYPES (ID, NAME) values (1, 'Entree');
INSERT INTO RECIPE_TYPES (ID, NAME) values (2, 'Dessert');

-- add a recipe
INSERT INTO RECIPES (ID, NAME, TYPE_ID) values (1, 'Pistachio Dessert', 2);

-- add ingredients to a recipe
INSERT INTO RECIPE_INGREDIENTS (RECIPE_ID, INGREDIENT_ID, VOLUME) values (1, 6, '1 cup');
INSERT INTO RECIPE_INGREDIENTS (RECIPE_ID, INGREDIENT_ID, VOLUME) values (1, 10, '1/2 cup');
INSERT INTO RECIPE_INGREDIENTS (RECIPE_ID, INGREDIENT_ID, VOLUME) values (1, 4, '1 tablespoon');
INSERT INTO RECIPE_INGREDIENTS (RECIPE_ID, INGREDIENT_ID, VOLUME) values (1, 3, '1 stick');
INSERT INTO RECIPE_INGREDIENTS (RECIPE_ID, INGREDIENT_ID, VOLUME) values (1, 7, '8 oz');
INSERT INTO RECIPE_INGREDIENTS (RECIPE_ID, INGREDIENT_ID, VOLUME) values (1, 5, '1 cup');
INSERT INTO RECIPE_INGREDIENTS (RECIPE_ID, INGREDIENT_ID, VOLUME) values (1, 8, '9 oz');
INSERT INTO RECIPE_INGREDIENTS (RECIPE_ID, INGREDIENT_ID, VOLUME) values (1, 9, '2 (3.5 oz) pkg');
INSERT INTO RECIPE_INGREDIENTS (RECIPE_ID, INGREDIENT_ID, VOLUME) values (1, 1, '3 cups');



