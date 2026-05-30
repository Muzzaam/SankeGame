# 🏆 Snake Game AI: Wits Competition Winner

> **1st Place Solution** for the Analysis of Algorithms PvP Snake Competition (2nd Year Computer Science, University of the Witwatersrand).

This repository contains my winning autonomous AI agent for a competitive multiplayer version of the classic Snake game you would find on old Nokia phones.

By winning matches, agents climbed a global leaderboard against every other student taking the course. **My solution ultimately won the entire competition.**

## The Environment

The game places your snake on a map with several hazards and competitors. The game runs for a set time limit (about 5 minutes), and the goal is to be the longest snake on the board when the clock runs out. 

The board consists of:
* **4 Player Snakes:** Your agent competing simultaneously against 3 other students' agents.
* **3 Zombie Snakes:** Hostile NPC snakes that actively hunt you down.
* **Map Obstacles:** Static walls scattered around the board that result in death upon collision.

## The Winning Algorithm: Voronoi Space Control

My algorithm's logic is simple: **the snake goes for the apple if it is the closest one to it, and if it isn't, it tries to move into the biggest open space.** 

While this is easy enough for a human to understand, translating "moving into the biggest open space" into code is quite a challenge. 

### How it Works:
To solve this, my code dynamically creates a **Voronoi diagram** of the game board at each timestep. It then simulates that diagram for the next possible moves. 

This approach is conceptually simple, but its spatial control ended up consistently outperforming more complex A* pathfinding algorithms and many other creative solutions built by the rest of the cohort.




<img width="1262" height="888" alt="image" src="https://github.com/user-attachments/assets/633a5a28-1993-4a1f-ae07-77944d014ec1" />




