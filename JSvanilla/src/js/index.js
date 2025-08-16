// src/index.js
import { initLinker } from './components/linker.js';

// module scripts are deferred, DOM is available now
document.title = document.body.dataset.title ?? 'MusicDL Backend Program';

// boot the linker (controller)
initLinker();
