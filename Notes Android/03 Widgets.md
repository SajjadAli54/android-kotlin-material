# Lecture 03

# Widgets

## TextView

### Properties

- id
- width and height
  - wrap_content
  - match_parent
  - dpi
  - spi
  - px
- onClick
- tag
- layout_weight
- layout_gravity
- text

`res/values` for localization

use as `@string/app_name`

`onCreate` -> responsible for layout creation

`res/layout` -> create layouts

### Constraints

Apply those which you need, not all

### Code

```
val myTextView: TextView = findViewById(R.id.txtName);
myTextView.text = "Walaikum salam"
var name: String = myTextView.text.toString()
```

Check messages using `Logcat`

#### global variable

```
var myTextView : TextView? = null // not preferred

lateinit var txtView: TextView
```

#### Parsing

`var number : Int = Integer.parseInt(value)`

## ImageView

Put `images` in `drawable` folder

name must be in all `small, underscore or numbers`

### Properties

1. `srcCombat` -> Give images source

## Event Handler

### Constraints

1. Public
2. void return type
3. takes an argument of View type

```
fun clickMe(view: View){
    Log.d("Counter ${++counter}")
}
```
