package com.androluna;

import com.androluna.util.*;
import com.luajava.*;

public class LuaTimer extends TimerX implements LuaGcable {

  private LuaTimerTask task;

  public LuaTimer(LuaContext main, String src) throws LuaException {
    this(main, src, null);
  }

  public LuaTimer(LuaContext main, String src, Object[] arg) throws LuaException {
    super("LuaTimer");
    main.regGc(this);
    task = new LuaTimerTask(main, src, arg);
  }

  public LuaTimer(LuaContext main, LuaObject func) throws LuaException {
    this(main, func, null);
  }

  public LuaTimer(LuaContext main, LuaObject func, Object[] arg) throws LuaException {
    super("LuaTimer");
    main.regGc(this);
    task = new LuaTimerTask(main, func, arg);
  }

  @Override
  public void gc() {
    // TODO: Implement this method
    stop();
  }

  public void start(long delay, long period) {
    schedule(task, delay, period);
  }

  public void start(long delay) {
    schedule(task, delay);
  }

  public void stop() {
    task.cancel();
  }

  public boolean isEnabled() {
    return task.isEnabled();
  }

  public boolean getEnabled() {
    return task.isEnabled();
  }

  public void setEnabled(boolean enabled) {
    task.setEnabled(enabled);
  }

  public long getPeriod() {
    return task.getPeriod();
  }

  public void setPeriod(long period) {
    task.setPeriod(period);
  }
}