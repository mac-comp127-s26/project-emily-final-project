public class Ability {
  private BuildingType type;
  private int val;
  private Stat stat;
  private AbilityTrigger trigger;

  public static class AbilityBuilder {
    private final AbilityTrigger trigger;
    private final int val;
    private final Stat stat;
    private BuildingType type;

    public AbilityBuilder(AbilityTrigger trigger, int val, Stat stat) {
      this.trigger = trigger;
      this.val = val;
      this.stat = stat;
    }

    public AbilityBuilder adjacentType(BuildingType type) {
      this.type = type;
      return this;
    }

    public Ability build() {
      return new Ability(this);
    }
  }

  private Ability(AbilityBuilder builder) {
    trigger = builder.trigger;
    val = builder.val;
    stat = builder.stat;
    type = builder.type;
  }

  /**
   * Return the type of adjacent card that matters for the ability.
   * 
   * @return
   */
  public BuildingType getAdjacentType() {
    return type;
  }

  /**
   * Return the name of the adjacent card type that matters for the ability.
   * 
   * @return
   */
  public String getAdjacentTypeName() {
    if (type == BuildingType.COMMERCIAL)
      return "Commercial";
    else if (type == BuildingType.COMMUNITY)
      return "Community";
    return null;
  }

  /**
   * Return the value of change per adjacent card of the right type.
   * 
   * @return
   */
  public int getChange() {
    return val;
  }

  /**
   * Return the stat that changes per adjacent card of the right type.
   * 
   * @return
   */
  public Stat getStat() {
    return stat;
  }

  /**
   * Returns the trigger that activates the ability.
   */
  public AbilityTrigger getTrigger() {
    return trigger;
  }
}

