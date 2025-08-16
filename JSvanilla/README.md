# MusicDL Backend Program - Web Conversion

This is a web conversion of the Java Swing MusicDL Backend Program, maintaining the exact same semantics and functionality while using modern web development conventions.

## Structure

### Files
- `public/linker.html` - ViewLinker converted to HTML structure
- `src/js/linker.js` - Main application entry point
- `src/css/styles.css` - Styling equivalent to Swing layout

### Modules (`src/js/modules/`)
- `ViewLinkerConstants.js` - Constants and static methods from ViewLinker
- `ModalDialog.js` - JOptionPane equivalent for message dialogs
- `ControllerLinker.js` - Controller logic and event handling
- `index.js` - Module exports

## How to Run

1. Start the server: `node app.js`
2. Visit: `http://localhost:3000` (redirects to `/linker.html`)
3. Or directly visit: `http://localhost:3000/linker.html`

## Semantic Equivalents

| Java Swing | Web Equivalent | Purpose |
|------------|----------------|---------|
| `ViewLinker` class | `linker.html` + `ViewLinkerConstants.js` | UI structure and constants |
| `ControllerLinker` class | `ControllerLinker.js` | Logic and event handling |
| `JOptionPane.showMessageDialog()` | `ModalDialog.js` | Message dialogs |
| `GridLayout(3,1,0,10)` | CSS Grid layout | Main container layout |
| `FlowLayout` | CSS Flexbox | Row layouts |
| `FocusAdapter` | `focus`/`blur` events | Placeholder text behavior |
| `ActionListener` | `click` event | Button click handling |

## Features

- ✅ Exact same layout and dimensions (680x480)
- ✅ Same placeholder text behavior
- ✅ Same validation logic
- ✅ Same message dialogs (Info/Warning)
- ✅ Same constants and getter methods
- ✅ Modular, reusable code structure
- ✅ Modern web development conventions

## Module Usage

```javascript
import { ControllerLinker, ViewLinkerConstants, ModalDialog } from './modules/index.js';

// Access constants
console.log(ViewLinkerConstants.TITLE);

// Show message dialog
ModalDialog.showMessageDialog("Message", "Title", "INFO");

// Create controller
const controller = new ControllerLinker();
```