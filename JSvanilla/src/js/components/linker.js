// src/linker.js
/**
 * initLinker()
 * - Reads configuration from HTML data attributes (document.body.dataset)
 * - Uses direct element globals (window.txfUrl) if available, otherwise falls back to getElementById
 * - Wires focus/blur/enter/click behavior
 */
export function initLinker() {
    // read data-* config from the HTML (Option B)
    const PLACEHOLDER = document.body.dataset.placeholder ?? 'Paste URL here';
  
    // prefer named globals but fallback to getElementById
    const txf = window.txfUrl ?? document.getElementById('txfUrl');
    const btn = window.btnGo ?? document.getElementById('btnGo');
    const lblTerms = window.lblTerms ?? document.getElementById('lblTerms');
  
    if (!txf || !btn || !lblTerms) {
      console.warn('Linker: missing DOM elements (txfUrl, btnGo, lblTerms)');
      return;
    }
  
    // initialize placeholder if input empty
    if (!String(txf.value).trim()) {
      txf.value = PLACEHOLDER;
      txf.classList.add('placeholder');
    }
  
    // focus/blur handlers (mirrors Java FocusAdapter behavior)
    function onFocus() {
      if (txf.value === PLACEHOLDER) {
        txf.value = '';
        txf.classList.remove('placeholder');
      }
    }
    function onBlur() {
      if (txf.value.trim() === '') {
        txf.value = PLACEHOLDER;
        txf.classList.add('placeholder');
      }
    }
  
    // Enter key triggers GO
    function onKeydown(ev) {
      if (ev.key === 'Enter') {
        ev.preventDefault();
        handleGo();
      }
    }
  
    // click handler
    function onClick() {
      handleGo();
    }
  
    // main action (same semantics as Java code)
    function handleGo() {
      const raw = txf.value;
      if (raw !== PLACEHOLDER && raw && raw.trim() !== '') {
        // info dialog; browser-native alert used for simplicity
        alert(`URL set to: ${raw}`);
      } else {
        alert('Please enter a valid URL.');
      }
    }
  
    // wire events
    txf.addEventListener('focus', onFocus);
    txf.addEventListener('blur', onBlur);
    txf.addEventListener('keydown', onKeydown);
    btn.addEventListener('click', onClick);
  
    // return a small API for cleanup if needed
    return {
      destroy() {
        txf.removeEventListener('focus', onFocus);
        txf.removeEventListener('blur', onBlur);
        txf.removeEventListener('keydown', onKeydown);
        btn.removeEventListener('click', onClick);
      }
    };
  }
  