import java.util.ArrayList;
import java.util.List;

public class Ability {
  private AbilityTrigger trigger;
  private List<Integer> vals = new ArrayList<>();
  private List<Stat> stats = new ArrayList<>();
  private BuildingType type;

  public static class AbilityBuilder {
    private final AbilityTrigger trigger;
    private List<Integer> vals = new ArrayList<>();
    private List<Stat> stats = new ArrayList<>();
    private BuildingType type;

    public AbilityBuilder(AbilityTrigger trigger) {
      this.trigger = trigger;
    }

    public AbilityBuilder addAdjacentType(BuildingType type) {
      this.type = type;
      return this;
    }

    public AbilityBuilder addChange(int val, Stat stat) {
      this.stats.add(stat);
      this.vals.add(val);
      return this;
    }

    public Ability buildAbility() {
      return new Ability(this);
    }
  }

  private Ability(AbilityBuilder builder) {
    trigger = builder.trigger;
    vals = builder.vals;
    stats = builder.stats;
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
  public int getChange(int n) {
    return vals.get(n);
  }

  /**
   * Return the stat that changes per adjacent card of the right type.
   * 
   * @return
   */
  public Stat getStat(int n) {
    return stats.get(n);
  }

  public int getNumChanges() {
    return stats.size();
  }

  /**
   * Returns the trigger that activates the ability.
   */
  public AbilityTrigger getTrigger() {
    return trigger;
  }
}

