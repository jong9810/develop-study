window.onload = () => {
  const $$panelFaqContainer = document.querySelectorAll('.panel-faq-container'); // NodeList 객체로 가져옴.
  const $$panelFaqAnswer = document.querySelectorAll('.panel-faq-answer');
  const $btnAllClose = document.querySelector('#btn-all-close');

  for (let i = 0; i < $$panelFaqContainer.length; i++) {
    $$panelFaqContainer[i].addEventListener('click', () => {
      $$panelFaqAnswer[i].classList.toggle('active');
    });
  }

  $btnAllClose.addEventListener('click', () => {
    for (let i = 0; i < $$panelFaqContainer.length; i++) {
      $$panelFaqAnswer[i].classList.remove('active');
    }
  });
};
