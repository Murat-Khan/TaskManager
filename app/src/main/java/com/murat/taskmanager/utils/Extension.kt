package com.murat.taskmanager.utils
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.murat.taskmanager.R


fun Context.showToast(text : String){
    Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url : String?){
    Glide.with(this.context).load(url).into(this)
}
fun Activity.showConfirmDialog(
    content:CharSequence,
    positiveBtnId : Int = R.string.yes,
    negativeBtnId: Int = R.string.no,
    onOkListener: () -> Unit = { }
): AlertDialog {
    val builder = AlertDialog.Builder(this, R.style.MyDialogStyle)
    val inflater : LayoutInflater = layoutInflater
    val dialogView : View = inflater.inflate(R.layout.castom_dialog,null)
    builder.setView(dialogView)
    builder.setCancelable(false)

    val title : TextView = dialogView.findViewById(R.id.dialog_title)
    val delete : TextView = dialogView.findViewById(R.id.dialog_delete)
    val confirm : TextView = dialogView.findViewById(R.id.dialog_confirm)

    val dialog = builder.create()
    title.text = content
    confirm.setOnClickListener {
        onOkListener()
        dialog.dismiss()
    }
    confirm.setText(positiveBtnId)
    delete.setOnClickListener { dialog.dismiss() }
    delete.setText(negativeBtnId)
    dialog.show()
    return dialog

}
