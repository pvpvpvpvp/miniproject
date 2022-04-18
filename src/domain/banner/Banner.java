package domain.banner;

public class Banner {
    private static StringBuilder sb;

    public void showBanner() {
        sb = new StringBuilder();

        sb.append(" ___  ___  _______   ________ _________  ________     \n");
        sb.append("|\\  \\|\\  \\|\\  ___ \\ |\\   ____\\\\___   ___\\\\   __  \\    \n");
        sb.append("\\ \\  \\\\\\  \\ \\   __/|\\ \\  \\___\\|___ \\  \\_\\ \\  \\|\\  \\   \n");
        sb.append(" \\ \\   __  \\ \\  \\_|/_\\ \\  \\       \\ \\  \\ \\ \\  \\\\\\  \\  \n");
        sb.append("  \\ \\  \\ \\  \\ \\  \\_|\\ \\ \\  \\____   \\ \\  \\ \\ \\  \\\\\\  \\ \n");
        sb.append("   \\ \\__\\ \\__\\ \\_______\\ \\_______\\  \\ \\__\\ \\ \\_______\\\n");
        sb.append("    \\|__|\\|__|\\|_______|\\|_______|   \\|__|  \\|_______|\n");

        System.out.println(sb.toString());
    }
}
