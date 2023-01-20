package schedule.heuristics.perturbative;

import base.Graph;

import java.util.List;

public interface DiffColorPairCreation {
    public List<DiffColorPair> createDiffColorPairs(Graph graph);
}
