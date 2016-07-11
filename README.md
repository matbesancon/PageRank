Functional version of PageRank in Scala, leveraging the parallel computation
capabilities of the language. The last plot was done using Python and Matplotlib.

## Results

The distribution of ranks follows as expected a power low, visible
on a log-log plot
[here](https://github.com/mbesancon/PageRank/blob/master/rank_dist.pdf)

## Test data

Tests were realized on the Enron Email dataset taken from the SNAP project
[website](https://snap.stanford.edu/data/email-Enron.html). Email addresses are
nodes and the data contains an edge from i to j if at least one email has been
sent. The data contains 36692 nodes 183831 edges.
