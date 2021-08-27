Louis Wellock. Please see the code in Grid.java for more context
Terrain Generation (bonus / no marks)
OCD was kicking in looking at the random map...

Start by generating grass everywhere

Useful code:
    Normal Vector [x,y] for each edge
In a formation like this?
     v v v
    >     <
    >     <
     ^ ^ ^
So we have bias for water streams and roads.

Grass starts everywhere
Mountains 
Water randomly selects edge tiles (flip for X/Y dominant) | (i%2 * width) & (j%2 * height) (random increment based on random()*height floor)
Generate float [x,y] with Direction of water. 
Move across until the edge of the map is reached for those XY values.
Any cell it crosses over (floor of x/y sum offset by startpos) is set to water.

Road we randomly select Grass edge tiles and make a straight line across them
5% chance we make an intersect with width (1-2) on the tangent vector.

Along the sides of the road, if there's grass. We place a building at a 20% chance.

Mountains we generate in corners and cluster them together. Ignoring everything, because they're the boss of this gym.
