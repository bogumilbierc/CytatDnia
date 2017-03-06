package jcrusader.pl.cytatdnia;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import jcrusader.pl.cytatdnia.dto.QuoteDto;
import jcrusader.pl.cytatdnia.task.DownloadQuoteTask;

public class QuoteActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private TextView authorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        assignViewsToObjects();
        downloadAndDisplayQuote();
    }

    private void assignViewsToObjects() {
        quoteTextView = (TextView) findViewById(R.id.quoteTextView);
        authorTextView = (TextView) findViewById(R.id.authorTextView);
    }

    private void downloadAndDisplayQuote() {
        new DownloadQuoteTask(this).execute();
    }

    public void updateQuoteViews(QuoteDto quoteDto) {
        if (quoteDto != null) {
            quoteTextView.setText(quoteDto.getQuote());
            authorTextView.setText(quoteDto.getAuthor());
        } else {
            showErrorAlert();
        }
    }

    private void showErrorAlert() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(getString(R.string.alertDialogTitle))
                .setMessage(R.string.alertDialogMessage)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
    }

}
