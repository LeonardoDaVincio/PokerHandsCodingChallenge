import java.util.Comparator;
import java.util.List;

class ListComparator<Card extends Comparable<Card>> implements Comparator<List<Card>> {

    @Override
    public int compare(List<Card> o1, List<Card> o2) {
        if (o1.size() > o2.size()) {
            return 1;
        } else if (o1.size() < o2.size()) {
            return -1;
        } else {
            return o1.get(0).compareTo(o2.get(0));
        }
    }

}
