export const getTodayGreeting = () => {
  const hour = new Date().getHours();
  if (hour < 12) {
    return 'Morning';
  } else if (hour < 18) {
    return 'Afternoon';
  } else {
    return 'Evening';
  }
};

// export function ddmmyyyyToDate(date: string): Date {
//   const [day, month, year] = date.split('/');
//   return new Date([month, day, year].join('/'));
// }

export function getMonthNameFromDate(dateString: string): string {
  const dateObject = new Date(dateString);
  const monthName = dateObject.toLocaleString('en-US', { month: 'short' });
  return monthName;
}

export function getDayFromDate(dateString: string): number {
  const dateObject = new Date(dateString);
  const dayOfMonth = dateObject.getDate();
  return dayOfMonth;
}

export function getQuizTime(dateString: string) {
  const dateObject = new Date(dateString);
  const hours = dateObject.getHours();
  const minutes = dateObject.getMinutes();
  const formattedTime = `${hours.toString().padStart(2, '0')}:${minutes
    .toString()
    .padStart(2, '0')}`;

  return formattedTime;
}
