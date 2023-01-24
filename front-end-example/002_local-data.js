window.onload = () => {
  const $btnSetData = document.getElementById('btn-set-data');
  const $btnGetData = document.getElementById('btn-get-data');
  const $input = document.querySelector('#input');
  let localData = '';

  $btnSetData.addEventListener('click', () => {
    if ($input.value === '') return;
    localData = $input.value;
    $input.value = '';
  });
  $btnGetData.addEventListener('click', () => {
    $input.value = localData;
  });
};
