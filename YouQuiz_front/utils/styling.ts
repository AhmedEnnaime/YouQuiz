export function getButtonClassName(type: string): string {
  let className: string = 'rounded-full px-4 py-2 ';
  switch (type) {
    case 'primary':
      className +=
        ' bg-blue-500 text-white hover:bg-blue-600 border-solid border-2 border-blue-500';
      break;
    case 'success':
      className +=
        ' bg-green-500 text-white hover:bg-green-600 border-solid border-2 border-green-500';
      break;
    case 'disabled':
      className +=
        ' bg-gray-300 text-gray-700 hover:bg-gray-400 border-solid border-2 border-gray-300';
      break;
    default:
      className +=
        'bg-white text-gray-700 hover:bg-gray-100 border-solid border-2 border-gray-200';
      break;
  }
  return className;
}

export function getProgressForeground(type: string): string {
  let className: string = '';
  switch (type) {
    case 'primary':
      className += 'bg-blue-600';
      break;
    case 'danger':
      className += 'bg-red-600';
      break;
    case 'success':
      className += ' bg-green-600 ';
      break;
    case 'disabled':
      className += ' bg-gray-400';
      break;
    default:
      className += 'bg-white';
      break;
  }
  return className;
}

export function getProgressBackground(type: string): string {
  let className: string = '';
  switch (type) {
    case 'primary':
      className += 'bg-blue-300';
      break;
    case 'danger':
      className += 'bg-red-300';
      break;
    case 'success':
      className += ' bg-green-300 ';
      break;
    case 'disabled':
      className += ' bg-gray-200';
      break;
    default:
      className += 'bg-gray-200';
      break;
  }
  return className;
}

export function getProgressText(type: string): string {
  let className: string = '';
  switch (type) {
    case 'primary':
      className += 'text-blue-600';
      break;
    case 'danger':
      className += 'text-red-600';
      break;
    case 'success':
      className += ' text-green-600 ';
      break;
    case 'disabled':
      className += ' text-gray-400';
      break;
    default:
      className += 'text-gray-600';
      break;
  }
  return className;
}
