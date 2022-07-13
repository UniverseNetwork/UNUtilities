package id.universenetwork.utilities.Universal.Utils;

import com.google.gson.JsonParser;
import id.universenetwork.utilities.bukkit.utils.Logger;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;

@UtilityClass
public class GitHubLatestCommit {
    public String check(String ownerRepo, String repoName) throws IOException {
        try {
            return JsonParser.parseReader(new BufferedReader(new InputStreamReader(
                            new URL("https://api.github.com/repos/" + ownerRepo + "/" + repoName + "/commits")
                                    .openConnection().getInputStream()))).getAsJsonArray()
                    .get(1).getAsJsonObject().get("sha").getAsString().substring(0, 7);
        } catch (IOException e) {
            Logger.log(Level.SEVERE, "An error occurred connecting to api.github.com, is it down?", e);
            throw new IOException("");
        }
    }
}