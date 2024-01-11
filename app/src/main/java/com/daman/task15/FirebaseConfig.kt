package com.daman.task15

import com.google.firebase.database.FirebaseDatabase

class FirebaseConfig {
    companion object {
        fun getDatabaseReference() = FirebaseDatabase.getInstance().reference
    }
}
