# 🏆 Snake Game AI: Wits Competition Winner

> **1st Place Solution** for the Analysis of Algorithms PvP Snake Competition (2nd Year Computer Science, University of the Witwatersrand).

This repository contains my winning autonomous AI agent for a highly competitive, multiplayer version of the classic Snake game. While the core mechanics mimic the nostalgic Nokia phone game, the environment is significantly more hostile and complex. 

By winning matches, agents climbed a global leaderboard against every other student taking the course. **My solution ultimately won the entire competition.**

## ⚔️ The Environment

The game places your snake on a map with several hazards and competitors. The game runs for a set time limit (about 5 minutes), and the goal is to be the longest snake on the board when the clock runs out. 

The board consists of:
* **4 Player Snakes:** Your agent competing simultaneously against 3 other students' agents.
* **3 Zombie Snakes:** Hostile NPC snakes that actively hunt you down.
* **Map Obstacles:** Static hazards scattered around the board that result in instant death upon collision.

## 🧠 The Winning Algorithm: Voronoi Space Control

My algorithm's logic is simple: **the snake goes for the apple if it is the closest one to it, and if it isn't, it tries to move into the biggest open space.** 

While this is easy enough for a human to understand, translating "moving into the biggest open space" into code is quite a challenge. 

### How it Works:
To solve this, my code dynamically creates a **Voronoi diagram** of the game board at each timestep. It then simulates that diagram for the next possible moves. 

1. **Calculate Area:** The agent evaluates the diagram to see which direction yields the highest cell count of "my space" (meaning the portion of the Voronoi diagram that safely belongs to my snake).
2. **Execute Move:** It chooses the direction that maximizes this territory.

This approach is conceptually simple, but its robust spatial control ended up consistently outperforming more complex A* pathfinding algorithms and many other creative solutions built by the rest of the cohort.
