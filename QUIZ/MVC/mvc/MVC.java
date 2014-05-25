package mvc;

public class MVC {

	public static void main(String[] args) {
		
		Model model = new Model();
		View view = new View(model);
		SQL sql = new SQL(view);
		@SuppressWarnings("unused")
		Controller controller = new Controller(model, view, sql);

		view.setVisible(true);
	}

}
