package com.example.insertartistsdata

class Artist() {
    private lateinit var _artistId: String
    private lateinit var _artistName: String

    private lateinit var _artistGenre: String

    constructor(artistId: String, artistName: String, artistGenre: String) : this() {
        this.artistId = artistId
        this.artistName = artistName
        this.artistGenre = artistGenre
    }

    var artistId: String
        get() = _artistId
        set(value) {
            _artistId = value
        }

    var artistName: String
        get() = _artistName
        set(value) {
            _artistName = value
        }

    var artistGenre: String
        get() = _artistGenre
        set(value) {
            _artistGenre = value
        }
}