# Project 4 — Virtual World Simulation Project

Virtual World Simulation Project
This project integrates three major improvements: code cohesion, pathing strategies, and the addition of a world-changing event.

1. Refactoring for Cohesion:
- Refactor a large object-oriented codebase to improve class cohesion by splitting low-cohesion classes into multiple, highly cohesive ones.
- Eliminate the use of enumerated types (ActionKind, EntityKind) by creating new classes that represent specific roles.
- Introduce abstract classes and interfaces to reduce code duplication, organize common methods, and maintain the existing functionality of the code.
  
2. Pathing Strategy and A Algorithm:*
- Modify the movement behavior of entities using the PathingStrategy interface, incorporating Java streams (filter and collect).
- Implement the A pathfinding algorithm* as a new PathingStrategy subclass to enhance entity navigation around obstacles.
- Ensure entities (e.g., Dudes and Fairies) use the new pathfinding strategy to avoid getting stuck, and write unit tests to verify correct functionality.
  
3. World-Changing Event:
- Add a "world-changing" event triggered by a mouse click that affects at least 7 nearby tiles and impacts existing mobile entities.
- Change the appearance and behavior of nearby entities based on their proximity to the event.
- Create and animate a new type of mobile entity that spawns as a result of the event, enhancing the world’s interactivity and visual effects.
