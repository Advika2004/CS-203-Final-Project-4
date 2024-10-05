# Project 4 — Final Project

- For this assignment you will modify the virtual world to support a “world-changing” event. This event is to be triggered by a mouse press and must have a localized effect. The event must be visualized by changing the affected background tiles and by modifying the affected entities (more below). In addition, the world event must create a new type of entity.

- In the example below, clicking the mouse causes a randomly shaped mountain range to appear with a dragon hiding in the mountains. The dragon flies off to eat any miners, blobs, or blacksmiths. (Note: To complete all the requirements of the assignment, the example would also need to have the event trigger a change to some or all members of an existing entity type.)


Objectives:
- Add new functionality to existing code base demonstrating an understanding of the existing design and functionality
- Be able to evaluate the current design based on the experience of adding to the code
- You may work with one partner on this project. Choose the code base of one of the partners to add to, and then both work on the project together (you will need to make a separate, private github repo if you want to collaborate. Then you can either have one of you copy and paste the code into the original github repo you’ve been using all quarter to submit, or add me as a collaborator to the new private repository so that I can clone it. Both team members must contribute code to the project.

Your Goal: World Changing Event
- Now that you have an excellent code base, you can edit it and grow the functionality by adding the following events.

World Changing Event: Visualization
- Decide on a world-changing event (e.g., a river spawns, a volcano erupts, or a rainbow is cast across the land). This event must be triggered by a mouse-click and must affect at least 7 tiles of the world in proximity to the mouse position when the click occurs. The event should affect no more than half of the world.
- The world event must be visualized by modifying the background image of the affected tiles (so edit Background objects instead of Entity objects for this step. You are free to modify them however you would like, and are encouraged to be creative.

World Changing Event: Effect
- At least one type of existing mobile entity (e.g., fairies or dudes) must be affected by the world event, based on proximity to the event location. More specifically, this type of entity should change in appearance and behavior (similar to how dudes transform from DudeNotFull to DudeFull).
- For example, a rainbow might change nearby dudes into dragons that seek to burn down houses.
- The affected/transformed entities should change appearance and should change behavior (and the behavior must be active..it cannot be that it was moving before and now does not move). You must have both for full credit.

World Changing Event: New Entity
- The world event must cause a new type of mobile entity to spawn. This new entity should animate and move according to logic defined by you. Make sure you have multiple image files so it animates.
- For instance, the new entity might seek out fairies to turn them into crystals, chase down dudes to infect them with the plague, or travel the world spreading apple seeds.

Note: This new entity is in addition to the entity transformation triggered by the event as just discussed. For example, a Dude transforming into a completely different kind of entity does not count for this requirement. A new entity must spawn (i.e. there are now more things in the world, not just an existing thing replaced.)

Additional Requirements:
- The images for your changed background, affected entity, and new entity must be created by you (or found by you…you can find a gif and convert it to png or bmp files). You may not use any of the existing images that came with the project (including the wyvern). An exception is for your “affected entity”, you may alter that entity’s current image.
- For entities, you can download a gif from hereLinks to an external site. and then use this websiteLinks to an external site. to convert the gif into a series of images. Those images are what will be used to “animate” your entity. Be sure to re-size the images so they fit in the world! See the other images in the images folder to know what the size should be.
- It goes without saying that all additions to the world should be professionally appropriate. Humour is okay. Crassness is not. If you’re not sure, ask me (or err on the side of caution).

Description:
- Include a text file named WORLD_EVENT.txt in your submission that describes:
- how to trigger the event
- what should happen when the event is triggered
- what the affected entities should do
- what the new entity is and how it should behave
- (if applicable) the names of each partner and what each partner was responsible for
- If you ran into any design decisions or tradeoffs, talk about those too!

Design:
- Be sure to adhere to the design principles discussed this quarter. Refactor your code as needed, and resist the urge for quick hacks that would increase maintenance costs.
- You are encouraged to reflect on the quality of your design and the effort required to add the functionality for this assignment. How do you think this effort compares to that needed to add the same functionality to the originally given code? (Especially if you have, e.g., entities that move quite differently from the original set.)

Presentation:
- On the presentation date, your group should give a short presentation. You should:

Introduce yourselves:
- Demo your project, highlighting the background tiles, transformed entities, and new entities
- Explain who worked on what piece
- Show your new classes and functions in VirtualWorld.java and explain your design choices
- Ask for questions / be prepared to answer if there are any about your code choices or program running
  
Assignment Submission:
- Copy your code back into your Github classroom repo when done - your submission must include all source files (even those that were unchanged). Your instructor should be able to build your project based on the files submitted. You must also submit all image files, the image list, and the world save file (since this assignment requires changes to some/all of these). Please verify on Github.com that your files were all added (especially the image files). An explicit list of files is not given because you are creating new files for this assignment, so verify that you have submitted everything properly.
- If you are working with a partner, only one partner needs to submit to github (presumably the partner from whose code base you began this assignment).

Concurrent Modification Exception:
- Warning: Depending on the implementation of your world and your world-changing event, you may run into a concurrent modification exception. This stems (in the most likely case for this project) from modifying a list while iterating over the list. If you run into such an error, speak with your instructor to help resolve the issue. One means to correct the error might be to schedule a new action to happen at a delayed time (so that the modification of, for instance, the entities list is not done while iterating over the list).
- Commit and push your code to your project GitHub repository.

Your submission must include all source files (even those that were unchanged). Your grader should be able to build your project based on the files submitted. An explicit list of files is not given because you are creating new files for this assignment, so verify that you have submitted everything properly. Remove files from your repository that are no longer needed for your project (e.g. EntityKind.java).
