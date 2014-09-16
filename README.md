Chronicles of Sándor & Co.
============================
This project is made by me, Robert 'Tuszy' Motyka, and by Gábor 'Timere' Halász. This is not made for university, it is entirely made for our own entertainment.
It is a simple rpg game concept, with very basic functions. For now, it only simulates battles between the hero of the user, and random creatures. Technical functions are creating, saving and loading of a custom character (only the name and the class can be unique). Game functions include a fight button, simulating one round of battle, and the heal function, with yet unlimited heal (it is now only for testing purposes).

The hero can gain experience from defeating enemies, which is calculated based on the attributes of the enemy. As there is experience, so there is also a leveling system. Some attributes are calculated automatically, but on given levels the user can increase one attribute at a time.

Attributes are calculated as follows(for a newly generated, level 1 character):

-XP = 0

-level = 1

-strength, dexterity, constitution: random number between 1-3

-health = constitution * 30

-attack rating = strength * 5

-defence rating = dexterity * 5

Additional attributes of enemies (also level 1 calculations):

-status = 'calm' (enemies get enraged when their health gets below 20% of their full health)

-amount of xp it's worth = (full health / 2) + attack rating + defence rating

One round of battle:

1. The hero throws with a 20 sided dice. The number + hero attack rating has to be more than the enemy defence rating.

    If the hero throws a 20 (or 18 in case of raging enemy), then the attack is a critical.
    
2. In case of a successful attack, the hero throws with a 6 sided dice, which represents the damage. This is added to the attack rating, and this hets subtracted of the enemies current health.

    In case of a critical attack, the final damage gets doubled.
    
3. If the enemy is not killed by the hero's attack, then it throws the same dices, same logic is here as well, inlcuding the critical attack.

    If the enemy is in an enraged status, it's defence rating is reduced to it's 2/3 of total defence rating, but gets to throw twice with the damage dice.
    
4. If the enemy attack kills the hero, then a level 1 hero is generated.

Leveling of attributes:

-health increased by constitution * 8

-attack rating increased by strength attribute

-defense rating increased by dexterity attribute

-every third level, user can decide to increase strength, dexterity, or constitution by 1
