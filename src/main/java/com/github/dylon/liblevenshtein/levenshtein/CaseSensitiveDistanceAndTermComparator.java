package com.github.dylon.liblevenshtein.levenshtein;

/**
 * @author Dylon Edwards
 * @since 2.1.0
 */
public class CaseSensitiveDistanceAndTermComparator
	extends AbstractDistanceAndTermComparator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compare(final Candidate a, final Candidate b) {
		int c = a.distance() - b.distance();
		if (0 != c) return c;
		c = a.lowerTerm().compareTo(lowerTerm) - b.lowerTerm().compareTo(lowerTerm);
		if (0 != c) return c;
		c = a.term().compareTo(term) - b.term().compareTo(term);
		if (0 != c) return c;
		c = a.lowerTerm().compareTo(b.lowerTerm());
		if (0 != c) return c;
		return a.term().compareTo(b.term());
	}
}
