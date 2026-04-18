public class BankBusiness extends Card {
    
    public BankBusiness(String name, Type type, String description, StatTracker stats) {
        super(name, type, description);
    }

    @Override
    public void whenPlaced() {
        StatTracker.changeEcon(-2);
    }

    @Override
    public void endOfGame() {
       StatTracker.changeEcon(+3);
    }
    
}
