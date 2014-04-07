package com.github.dylon.liblevenshtein.levenshtein;

/**
 * @author Dylon Edwards
 * @since 2.1.0
 */
public interface IStateTransitionFunction {

  IState of(IState state, boolean[] characteristicVector);
}
