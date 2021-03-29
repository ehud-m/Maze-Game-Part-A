package algorithms.mazeGenerators;

public abstract class AmazeGenerator implements IMazeGenerator{

        public long measureAlgorithmTimeMillis (int row,int col){
        long TotalTime = 0;
        if (row > 0 && col >0 ){
            long time = System.currentTimeMillis();
            generate(row, col);
            TotalTime = time - System.currentTimeMillis();
        }
        else
            System.out.println("Number not in range");
            //throw Exception()

        return TotalTime;

    }

}
