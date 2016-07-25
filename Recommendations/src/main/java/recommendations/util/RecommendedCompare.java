package recommendations.util;

import java.util.Comparator;

import recommendations.resources.Recommended;

public class RecommendedCompare implements Comparator<Recommended> {

	@Override
	public int compare(Recommended o1, Recommended o2){
        if(o1.getRecent().size() == o2.getRecent().size())
            return 0;
        return o1.getRecent().size() > o2.getRecent().size() ? -1 : 1;
    }

}
