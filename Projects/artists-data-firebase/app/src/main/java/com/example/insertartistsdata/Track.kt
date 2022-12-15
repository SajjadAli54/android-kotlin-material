package com.example.insertartistsdata

class Track() {

    private var _id: String = ""
    var id: String
        get() = _id
        set(value) {
            _id = value
        }
    private var _trackName: String = ""
    var trackName: String
        get() = _trackName
        set(value) {
            _trackName = value
        }
    private var _rating: Int = 0
    var trackRating: Int
        get() = _rating
        set(value) {
            _rating = value
        }

    constructor(id: String, name: String, rating: Int): this(){
        this.id = id
        this.trackName = name
        this.trackRating = rating
    }
}