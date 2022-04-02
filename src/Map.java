public class Map {
    private int[][] map;
    public Map(int[][] input){
        this.map=input;
        IO.print("$ Memory: Map load Successfully.");
    }
    public int[][] GetMap(){
        return this.map;
    }
}
