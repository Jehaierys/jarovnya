// тут трудная часть

// let currentSlide = 0;

// function moveCarousel(direction) {
//   const carousel = document.querySelector(".carousel");
//   const totalItems = document.querySelectorAll(".carousel-item").length;

//   currentSlide += direction;

//   if (currentSlide < 0) {
//     currentSlide = totalItems - 1;
//   } else if (currentSlide >= totalItems) {
//     currentSlide = 0;
//   }

//   const slideWidth = carousel.querySelector(".carousel-item").clientWidth;
//   carousel.style.transform = `translateX(${-currentSlide * slideWidth}px)`;
// }

// попроще

// let currentSlide = 0; // индекс текущего слайда

// // элементы карусели и их количество
// const carousel = document.querySelector(".carousel");
// const slides = document.querySelector(".carousel-item");
// const totalSlides = slides.length;

// // функция для обновления отображаемого слайда
// function updateCarousel() {
//   // calculating shift
//   const offset = -currentSlide * 100;
//   carousel.style.transform = `translateX(${offset}%)`;
// }

// function moveCarousel(direction) {
//   currentSlide += direction;

//   if (currentSlide < 0) {
//     currentSlide = totalSlides - 1; // переход к последнему слайду
//   } else if (currentSlide >= totalSlides) {
//     currentSlide = 0;
//   }

//   updateCarousel();
// }

// document.querySelector(".prev").onclick = () => moveCarousel(-1);
// document.querySelector(".next").onclick = () => moveCarousel(1);

// еще один вариант
let currentSlide = 0;

function moveCarousel(direction) {
  const carousel = document.getElementById("carousel");
  const totalSlides = carousel.children.length;

  // Обновление текущего слайда
  currentSlide = (currentSlide + direction + totalSlides) % totalSlides;

  // Сдвиг карусели
  const offset = -currentSlide * 100;
  carousel.style.transform = `translateX(${offset}%)`;
}
