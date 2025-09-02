package jp.ac.meijou.android.s253426024;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s253426024.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.buttonChange.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            binding.textView.setText(text);
        });

        prefDataStore = PrefDataStore.getInstance(this);
//        prefDataStore.getString("name").ifPresent(name -> binding.textView.setText(name));
        binding.buttonSave.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString("name", text);
        });

//        binding.editTextText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // テキストが更新される前に呼ばれる
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // 文字を1つ入力された時に呼ばれる
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // テキストが更新された後に呼ばれる
//                binding.textView.setText(s.toString());
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        prefDataStore.getString("name").ifPresent(name -> binding.textView.setText(name));
    }
}