package book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages a collection of Recommendation objects.
 * Supports adding, retrieving, cloning, and removing recommendations.
 */
public class RecommendationManager {

    private final List<Recommendation> recommendations = new ArrayList<>();

    /** Add a recommendation to the system. */
    public void save(Recommendation recommendation) {
        recommendations.add(recommendation);
    }

    /** Get all recommendations (unmodifiable view). */
    public List<Recommendation> getAll() {
        return Collections.unmodifiableList(recommendations);
    }

    /** Get a recommendation by 1-based index. Returns null if index is out of range. */
    public Recommendation get(int oneBased) {
        if (oneBased < 1 || oneBased > recommendations.size()) return null;
        return recommendations.get(oneBased - 1);
    }

    /**
     * Clone a recommendation by index.
     * The clone is NOT automatically saved — the caller decides what to do with it.
     */
    public Recommendation cloneRecommendation(int oneBased) {
        Recommendation original = get(oneBased);
        if (original == null) return null;
        return original.clone();
    }

    /** Remove a recommendation by 1-based index. */
    public boolean remove(int oneBased) {
        if (oneBased < 1 || oneBased > recommendations.size()) return false;
        recommendations.remove(oneBased - 1);
        return true;
    }

    public int size() {
        return recommendations.size();
    }

    public boolean isEmpty() {
        return recommendations.isEmpty();
    }
}