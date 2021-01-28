public class Test
{

    public static void main(String[] args) {
      // int count = solution(6, 20);
       // System.out.println(count);
        System.out.println(solution("codility"));

    }
    public static int solution(int A, int B) {
            int count = 0;
            int aMultipleier = 1;
            boolean aFactorFound = false;
            if (A >= B || A == 0 || B == 0) {
                return count;
            }
            int aLowerFactor = 0;
            int aHigherFactor = 0;
            for (int i = 1; i < A / 2; i++) {
                aMultipleier = i * (i + 1);
                if (aMultipleier == A) {
                    aFactorFound = true;
                    aLowerFactor = i;
                    aHigherFactor = i + 1;
                    count = count + 1;
                    break;
                }
            }

            if (!aFactorFound) {
                return 0;
            }

            int bMultipler = 1;
            boolean bMultiplerFound = false;
            while (bMultipler <= B) {
                bMultipler = aHigherFactor * (aHigherFactor + 1);
                aHigherFactor++;
                count++;
                if (bMultipler == B) {
                    bMultiplerFound = true;
                    break;
                }
            }
            if (!bMultiplerFound) {
                return 0;
            }
            return count;
        }

    public static int solution(String S) {
        int distance = -1;
        if(S==null || S.equals(" ") || S.trim().equals("")  ){
            return distance;
        }
        boolean distanceFound = false;
        int lastMaxDistance = -1;
        for(int i = 0; i < S.length()-1;i++){
            String diagram = S.substring(i, i+2);
            int lastIndex = S.lastIndexOf(diagram);
            if(lastIndex!=-1 && lastIndex != i){
                 distance = lastIndex - i;
                distanceFound = true;
            }
            if(distance > lastMaxDistance){
                lastMaxDistance = distance;
            }
        }
      return (distanceFound) ? lastMaxDistance : -1;
    }
    }
