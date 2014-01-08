Bas-Relief-Creator
==================

Transforms any input mesh (as a .obj) into a bas-relief depth map (as an image).

What the program will do, in this order :

- parse an obj file to extract the faces (they must be triangles) and put them into an list.
- render the depth map (the camera will be by default in coordinates (0,0) and head toward (-z)).
- use an adaptative histogram equalization algorithm to convert this absolute depth map into a bas-relief depth map.
- convert this depth map (double array) into an black-white image.
