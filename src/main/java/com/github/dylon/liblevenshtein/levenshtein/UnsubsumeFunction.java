package com.github.dylon.liblevenshtein.levenshtein;

import lombok.Setter;
import lombok.experimental.Accessors;

import com.github.dylon.liblevenshtein.levenshtein.factory.IPositionFactory;

@Accessors(fluent=true)
public abstract class UnsubsumeFunction implements IUnsubsumeFunction {

  @Setter
  protected ISubsumesFunction subsumes;

  @Setter
  protected IPositionFactory positionFactory;

  public static class ForStandardPositions extends UnsubsumeFunction {

    @Override
    public void at(final IState state) {
      for (int m = 0; m < state.size(); ++m) {
        final int[] outer = state.getOuter(m);
        final int i = outer[0];
        final int e = outer[1];

        int n = m + 1;
        while (n < state.size()) {
          final int[] inner = state.getInner(n);
          final int f = inner[1];
          if (e < f) {
            break;
          }
          n += 1;
        }

        while (n < state.size()) {
          final int[] inner = state.getInner(n);
          final int j = inner[0];
          final int f = inner[1];

          if (subsumes.at(i,e, j,f)) {
            state.removeInner();
            positionFactory.recycle(inner);
          }
          else {
            n += 1;
          }
        }
      }
    }
  }

  public static class ForXPositions extends UnsubsumeFunction {

    @Override
    public void at(final IState state) {
      for (int m = 0; m < state.size(); ++m) {
        final int[] outer = state.getOuter(m);
        final int i = outer[0];
        final int e = outer[1];
        final int s = outer[2];

        int n = m + 1;
        while (n < state.size()) {
          final int[] inner = state.getInner(n);
          final int f = inner[1];
          if (e < f) {
            break;
          }
          n += 1;
        }

        while (n < state.size()) {
          final int[] inner = state.getInner(n);
          final int j = inner[0];
          final int f = inner[1];
          final int t = inner[2];

          if (subsumes.at(i,e,s, j,f,t, n)) {
            state.removeInner();
            positionFactory.recycle(inner);
          }
          else {
            n += 1;
          }
        }
      }
    }
  }
}